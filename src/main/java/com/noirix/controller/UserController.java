package com.noirix.controller;

import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users") //all methods mapping will start with /users
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    // /users
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView result = new ModelAndView();

        result.setViewName("users");
        result.addObject("users", userService.findAll());

        return result;
    }

    //  /users/search
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("query") String queryParam,
                               @RequestParam("limit") Long limit) {
        ModelAndView result = new ModelAndView();

        result.setViewName("users");
        result.addObject("users", userService.search(queryParam).stream().limit(limit).collect(Collectors.toList()));

        return result;
    }


}
