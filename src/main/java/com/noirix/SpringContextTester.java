package com.noirix;

import com.noirix.domain.User;
import com.noirix.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Collectors;

public class SpringContextTester {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));
    }
}
