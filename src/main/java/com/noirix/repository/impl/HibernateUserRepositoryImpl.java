package com.noirix.repository.impl;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Log4j2
public class HibernateUserRepositoryImpl implements HibernateUserRepository {

    private SessionFactory sessionFactory;

    public HibernateUserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<HibernateUser> findAll() {

        try(Session session = sessionFactory.openSession()) {

        }

        return Collections.emptyList();
    }

    @Override
    public List<HibernateUser> search(String query) {
        return null;
    }

    @Override
    public Optional<HibernateUser> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public HibernateUser save(HibernateUser object) {
        return null;
    }

    @Override
    public HibernateUser findById(Long key) {
        return null;
    }

    @Override
    public Optional<HibernateUser> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        return null;
    }

    @Override
    public Long delete(HibernateUser object) {
        return null;
    }
}
