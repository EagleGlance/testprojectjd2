package com.noirix.service.impl;


import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import com.noirix.repository.impl.UserSpringDataRepository;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class HibernateUserServiceImpl implements UserService {

    //private final HibernateUserRepository userRepository;

    private UserSpringDataRepository userRepository;

    public HibernateUserServiceImpl(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<HibernateUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public HibernateUser save(HibernateUser user) {
        //1. Validation layer
        //2. Convert http request params into HibernateUser object
        //3. Extended calls into DB or external services
        return userRepository.save(user);
    }

    @Override
    public HibernateUser update(HibernateUser user) {
        //return userRepository.update(user);
        return null;
    }

    @Override
    public HibernateUser findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<HibernateUser> search(String query) {
        //return userRepository.search(query); //Ctrl+Alb+B - go to implementation of method
        return Collections.emptyList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createSomeRow(Long userId, Long goodId) {
        userRepository.createSomeRow(userId, goodId);
        throw new RuntimeException();
    }
}
