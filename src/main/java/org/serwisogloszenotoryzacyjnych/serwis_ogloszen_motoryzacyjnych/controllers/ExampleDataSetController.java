package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleDataSetController extends Controller {
    @GetMapping("/exampledata")
    public void index(){
        User user=new User();
        user.setEmail("example@example.com");
        user.setFirstName("Name");
        user.setLastName("LastName");
        user.setPassword("password");
        user.setUsername("root");

        userService.store(user);
    }
}
