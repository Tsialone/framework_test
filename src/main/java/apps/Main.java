package apps;
import java.lang.reflect.Method;

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

    public static void main(String[] args) throws Exception {
        handleRequest("/home");
        handleRequest("/contact");
        handleRequest("/inconnu"); 
    }
}
