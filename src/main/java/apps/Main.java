package apps;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import annotations.ControllerAnnotation;
import annotations.UrlAnnotation;

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
        if (files == null) return classes;

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

    public static void main(String[] args) throws Exception {
        handleRequestPackage("/", "controllers");
    }
}
