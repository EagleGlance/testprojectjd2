package com.noirix.converter;

import com.noirix.controller.request.UserChangeRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.UserSpringDataRepository;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class UserEditRequestConverter extends EntityConverter<UserChangeRequest, HibernateUser> {

    private UserSpringDataRepository userRepository;

    public UserEditRequestConverter(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HibernateUser convert(UserChangeRequest request) {

        HibernateUser hibernateUser = userRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return doConvert(hibernateUser, request);
    }
}