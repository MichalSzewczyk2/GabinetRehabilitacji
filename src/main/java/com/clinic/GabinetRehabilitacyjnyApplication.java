package com.clinic;

import com.clinic.dbRepository.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class GabinetRehabilitacyjnyApplication {
    public static void main(String[] args){
        SpringApplication.run(GabinetRehabilitacyjnyApplication.class, args);
    }

    @GetMapping("/test")
    public List<String> hello(){
        User user = new User();
        user.setId(1);
        return List.of("Hello", "World");
    }
}
