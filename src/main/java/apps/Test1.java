package apps;

import annotations.ControllerAnnotation;
import annotations.UrlAnnotation;

@ControllerAnnotation(name = "xxxx")
public class Test1 {

    public Test1() {

    }

    @UrlAnnotation(value = "/hello")
    public String helloWorld() {
        return "hello";
    }   
}
