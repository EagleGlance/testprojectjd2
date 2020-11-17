package com.noirix.controller;

import com.noirix.controller.request.SearchCriteria;
import com.noirix.controller.request.UserChangeRequest;
import com.noirix.controller.request.UserCreateRequest;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {

        //return ResponseEntity.ok(userService.findAll());
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<User> userSearch(@ModelAttribute SearchCriteria search) {
        return userService.search(search.getQuery());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User savingUser(@RequestBody UserCreateRequest userCreateRequest) {
        //converters
        User user = new User();
        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userCreateRequest.getWeight());
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id,
                           @RequestBody UserCreateRequest userCreateRequest) {

        User user = userService.findById(id);

        //converters
        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userCreateRequest.getWeight());
        return userService.update(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody UserChangeRequest userChangeRequest) {

        User user = userService.findById(userChangeRequest.getId());

        //converters
        user.setGender(userChangeRequest.getGender());
        user.setName(userChangeRequest.getName());
        user.setSurname(userChangeRequest.getSurname());
        user.setBirthDate(userChangeRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userChangeRequest.getWeight());
        return userService.update(user);
    }


}
