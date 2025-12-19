package apps;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import annotations.ControllerAnnotation;
import annotations.UrlAnnotation;
import apps.models.Departement;
import apps.models.Type;
import utils.KeyValueUtil;
import utils.MapUtil;
import utils.ScannerUtil;
import views.ModelView;

public class Main {

    public static void handleRequestPackage(String url, String classPath) {
        try {
            List<Class<?>> classes = getClasses(classPath);

            for (Class<?> controller : classes) {
                if (controller.isAnnotationPresent(ControllerAnnotation.class)) {
                    System.out.println("Classe trouvée : " + controller.getName());

                    Object instance = controller.getDeclaredConstructor().newInstance();

                    for (Method m : controller.getDeclaredMethods()) {
                        if (m.isAnnotationPresent(UrlAnnotation.class)) {
                            UrlAnnotation uri = m.getAnnotation(UrlAnnotation.class);
                            if (uri.value().equals(url)) {
                                Object result = m.invoke(instance);
                                System.out.println(result);
                                return;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("404 - Page non trouvée");
    }

    private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        if (files == null)
            return classes;

        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }

    public static List<String> splitByStr(String path, String str) {
        List<String> resp = new ArrayList<>();
        String[] splited = path.split(str);
        for (String string : splited) {
            String trimString = string.trim();
            if (!trimString.isEmpty() && trimString.length() > 0) {
                resp.add(trimString);
            }
        }
        return resp;
    }

    // doit etre de la forme: key=1
    public static KeyValueUtil getKeyValueByParam(String str) {
        List<String> splited = splitByStr(str, "\\=");
        if (splited.size() == 2) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(splited.get(0), splited.get(1));
            KeyValueUtil keyValueUtil = new KeyValueUtil(splited.get(0), splited.get(1));
            return keyValueUtil;
        }
        return null;
    }

    public static HashMap<String, Object> getKeyValueByParamUrl(String param) {
        HashMap<String, Object> resp = new HashMap<>();

        List<String> splited = splitByStr(param, "\\&");
        for (String string : splited) {
            KeyValueUtil keyValueUtil = getKeyValueByParam(string);
            resp.put(keyValueUtil.getKey(), keyValueUtil.getValue());
        }
        return resp;
    }

    public static HashMap<String, Object> initKey(String uri, String controllerUrl, String regex) {
        List<String> sp1 = splitByStr(uri, regex);
        List<String> sp2 = splitByStr(controllerUrl, regex);
        if (regex.equals("\\/") && sp1.size() != sp2.size())
            return null;
        if (sp1.size() > 1)
            sp1.remove(0);
        sp2.remove(0);
        System.out.println("sp1: " + sp1);
        System.out.println("sp2: " + sp2);
        HashMap<String, Object> resp = new HashMap<>();

        if (regex.equals("\\/")) {
            for (int i = 0; i < sp1.size(); i++) {
                if (!sp2.get(i).contains("}") || !sp2.get(i).contains("{"))
                    continue;
                String newKey = sp2.get(i).replace("}", "").replace("{", "");
                resp.put(newKey, sp1.get(i));
            }
        } else if (regex.equals("\\?")) {
            return getKeyValueByParamUrl(sp1.getFirst());
        }

        return resp;
    }

    public static <T> void main(String[] args) throws Exception {

        // ScannerUtil scannerUtil = new ScannerUtil("apps");
        // HashMap<String , List<MapUtil>> temp = scannerUtil.getMapHash();

        Path p = Paths.get("/home/tsialone/uploads/test.txt");
        Files.write(p, "Hello world".getBytes());
        System.out.println("File saved: " + p.toAbsolutePath());
        System.out.println("Exists: " + Files.exists(p));

        // System.out.println(temp);

    }
}
