package com.noirix.service;

import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    List<HibernateUser> findAll();

    HibernateUser save(HibernateUser HibernateUser);

    HibernateUser update(HibernateUser HibernateUser);

    HibernateUser findById(Long HibernateUserId);

    List<HibernateUser> search(String query);

    int createSomeRow(Long userId, Long goodId);
}
