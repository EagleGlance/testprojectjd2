package com.noirix.controller;

import com.noirix.controller.request.SearchCriteria;
import com.noirix.controller.request.UserChangeRequest;
import com.noirix.controller.request.UserCreateRequest;
import com.noirix.domain.SystemRoles;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/users/hibernate")
@RequiredArgsConstructor
public class UserHibernateController {

    public final HibernateUserRepository hibernateUserRepository;

    @GetMapping
    public ResponseEntity<Object> findAllHibernateUsers(@ModelAttribute SearchCriteria criteria) {
        //Object o = hibernateUserRepository.testHql();
        //List<HibernateUser> all = hibernateUserRepository.findAll();
        List<HibernateUser> hibernateUsers = hibernateUserRepository.testCriteriaApi(criteria);

        return new ResponseEntity<>(hibernateUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser findUserById(@PathVariable Long id) {
        return hibernateUserRepository.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HibernateUser> userSearch(@ModelAttribute SearchCriteria search) {
        return hibernateUserRepository.search(search.getQuery());
    }


    @ApiOperation(value = "Endpoint for creation users")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
//            @ApiImplicitParam(name = "query", defaultValue = "query", required = false, paramType = "path", dataType = "string")
//    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HibernateUser savingUser(@RequestBody UserCreateRequest userCreateRequest) {
        //converters
        HibernateUser user = new HibernateUser();
        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userCreateRequest.getWeight());
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());

        //user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
        user.setRole(new HibernateRole(SystemRoles.ROLE_ADMIN, user));
        return hibernateUserRepository.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser updateUser(@PathVariable Long id,
                                    @RequestBody UserCreateRequest userCreateRequest) {

        HibernateUser user = hibernateUserRepository.findById(id);

        //converters
        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userCreateRequest.getWeight());
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());

        //user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
        user.setRole(new HibernateRole(SystemRoles.ROLE_ADMIN, user));
        return hibernateUserRepository.update(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser updateUser(@RequestBody UserChangeRequest userChangeRequest) {

        HibernateUser user = hibernateUserRepository.findById(userChangeRequest.getId());

        //converters
        user.setGender(userChangeRequest.getGender());
        user.setName(userChangeRequest.getName());
        user.setSurname(userChangeRequest.getSurname());
        user.setBirthDate(userChangeRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userChangeRequest.getWeight());


        return hibernateUserRepository.update(user);
    }

}
