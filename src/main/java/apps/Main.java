package apps;

import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import annotations.Controller;
import annotations.Url;
import controllers.UrlController;

public class Main {
    public static void handleRequest(String path) throws Exception {
        UrlController controller = new UrlController();

        for (Method m : UrlController.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Url.class)) {
                Url url = m.getAnnotation(Url.class);
                if (url.value().equals(path)) {
                    m.invoke(controller);
                    return;
                }
            }
        }
        System.out.println("404 - Page non trouvée");
    }

    public static void handleRequestPackage(String url, String classPath) throws Exception {

        try {
            Reflections reflections = new Reflections(
                    "apps",
                    new SubTypesScanner(false),
                    new TypeAnnotationsScanner());

            Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);


            for (Class<?> controller : controllers) {
                System.out.println("Classe trouvée : " + controller.getName());
                Object instance = controller.getDeclaredConstructor().newInstance();
                for (Method m : controller.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(Url.class)) {
                        Url uri = m.getAnnotation(Url.class);
                        if (uri.value().equals(url)) {
                            Object result = m.invoke(instance);
                            System.out.println(result);
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // UrlController controller = new UrlController();

        System.out.println("404 - Page non trouvée");
    }

    public static void main(String[] args) throws Exception {
        handleRequestPackage("/hello", "apps");
        // handleRequest("/home");
        // handleRequest("/contact");
        // handleRequest("/inconnu");
        // List<Class<?>> classes = getClasses("apps");
        // for (Class<?> cls : classes) {
        // System.out.println(cls.getName());

    }
}
