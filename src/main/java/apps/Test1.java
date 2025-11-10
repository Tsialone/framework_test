package apps;

import annotations.ControllerAnnotation;
import annotations.UrlAnnotation;
import views.ModelView;

@ControllerAnnotation(name = "xxxx")
public class Test1 {

    public Test1() {

    }

    @UrlAnnotation(value = "/hello")
    public String helloWorld() {
        return "hello";
    }

    @UrlAnnotation(value = "/test")
    public ModelView test() {
        ModelView mv = new ModelView();
        mv.setView("test.jsp");
        return mv;
    }
}
