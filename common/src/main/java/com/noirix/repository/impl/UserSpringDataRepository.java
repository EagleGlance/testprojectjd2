package com.noirix.repository.impl;

import com.noirix.domain.Credentials;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface UserSpringDataRepository extends JpaRepository<HibernateUser, Long> {

    List<HibernateUser> findByCredentials(Credentials credentials);
//
//    List<HibernateUser> findByLoginAndNameAndBirthDate(String login, String name, Date birthDate);
//
//    List<HibernateUser> findByLoginAndNameOrBirthDateOrderByIdDescNameDesc(String login, String name, Date birthDate);

    //select * from m_users where (login = ? and name = ?) or birth_date = ?

    @Query(value = "select u from HibernateUser u")
    List<HibernateUser> findByHQLQuery();

    @Query(value = "select * from m_users", nativeQuery = true)
    List<HibernateUser> findByHQLQueryNative();

    @Query(value = "select u from HibernateUser u where u.credentials.login = :login and u.name = :userName")
    List<HibernateUser> findByHQLQuery(String login, @Param("userName") String name);

    @Query("select u.id, u.name from HibernateUser u")
    List<Object[]> getPartsOfUser();

    /*TODO: transaction manager configuration required*/
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Modifying
    @Query(value = "insert into l_user_goods(user_id, good_id) values (:user_id, :good_id)", nativeQuery = true)
    int createSomeRow(@Param("user_id") Long userId, @Param("good_id") Long goodId);

    /*call function case*/
    //@SuppressWarnings("")
    @Query(value = "select * from smart_user_search(:gender, :firstName, :surname, :login, :id, :birthDate, :created, :changed)", nativeQuery = true)
    HibernateUser findUserWithFunctionCall(@Param("gender") String gender,
                                           @Param("firstName")String firstName,
                                           @Param("surname")String surname,
                                           @Param("login")String login,
                                           @Param("id")Long id,
                                           @Param("birthDate")Date birthDate,
                                           @Param("created")Timestamp created,
                                           @Param("changed")Timestamp changed);




}
