package com.noirix.repository.impl;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> search(String query) {
        return null;
    }

    @Override
    public User save(User object) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return Collections.singletonList(new User())/*jdbcTemplate.query("select * from m_users", )*/;
    }

    @Override
    public User findById(Long key) {
        return null;
    }

    @Override
    public Optional<User> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Long delete(User object) {
        return null;
    }
}
