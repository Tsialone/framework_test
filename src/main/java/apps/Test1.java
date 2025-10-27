package apps;

import annotations.Controller;
import annotations.Url;

@Controller(name = "xxxx")
public class Test1 {

    public Test1() {

    }

    @Url(value = "/hello")
    public String helloWorld() {
        return "hello";
    }   
}
