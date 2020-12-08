package com.noirix.repository.impl;

import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserSpringDataRepository extends JpaRepository<HibernateUser, Long> {

    List<HibernateUser> findByLogin(String login);

    List<HibernateUser> findByLoginAndNameAndBirthDate(String login, String name, Date birthDate);

    List<HibernateUser> findByLoginAndNameOrBirthDateOrderByIdDescNameDesc(String login, String name, Date birthDate);

    //select * from m_users where (login = ? and name = ?) or birth_date = ?

    @Query(value = "select u from HibernateUser u")
    List<HibernateUser> findByHQLQuery();

    @Query(value = "select * from m_users", nativeQuery = true)
    List<HibernateUser> findByHQLQueryNative();

    @Query(value = "select u from HibernateUser u where u.login = :login and u.name = :userName")
    List<HibernateUser> findByHQLQuery(String login, @Param("userName") String name);

    @Query("select u.id, u.name from HibernateUser u")
    List<Object[]> getPartsOfUser();

    /*TODO: check this annotation в четверг*/
    @Modifying(flushAutomatically = true)
    @Query(value = "insert into l_user_goods(user_id, good_id) values (:user_id, :good_id)", nativeQuery = true)
    int createSomeRow(@Param("user_id") Long userId, @Param("good_id") Long goodId);



}