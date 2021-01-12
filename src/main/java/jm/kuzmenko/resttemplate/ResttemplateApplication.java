package jm.kuzmenko.resttemplate;

import jm.kuzmenko.resttemplate.model.User;
import jm.kuzmenko.resttemplate.service.RestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResttemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResttemplateApplication.class, args);

        RestService restService = new RestService();

        System.out.println(restService.getAllUser());
        System.out.println(restService.addUser(new User(3L, "James", "Brown", 24)));
        System.out.println(restService.editUser(new User(3L, "Thomas", "Shelby", 24)));
        System.out.println(restService.deleteUser(3L));
    }
}
