package fi.tuni.blogproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String hello() {
        return "Hello the time of the server is now " + new Date() + "\n";
    }
}
