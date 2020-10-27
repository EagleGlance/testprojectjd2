package com.noirix;

import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.Collectors;

public class SpringContextTester {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        System.out.println(userService.findById(11L));
        System.out.println(userService.search("TestCreate"));


        User userForSave =
                User.builder()
                        .name("Viachaslau")
                        .surname("Kalevich")
                        .birthDate(new Date())
                        .created(new Timestamp(new Date().getTime()))
                        .changed(new Timestamp(new Date().getTime()))
                        .gender(Gender.MALE)
                        .weight(90f)
                        .build();

        System.out.println(userService.save(userForSave));

        //Add search method to service
        //Realise search method with JDBCTemplate or NamedParamJDBCTemplate
    }
}
