package exercise.controller;

import exercise.Application;
import exercise.daytime.Day;
import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private Daytime daytime;
    @GetMapping(path = "/welcome")
    public String welcome() {
        Daytime hour = Application.getHour();
        return "it is" + " " + hour.getName() + " " + "now! Welcome to Spring!";

    }
}
// END
