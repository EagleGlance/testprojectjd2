package com.noirix.repository.impl;

import com.noirix.controller.request.SearchCriteria;
import com.noirix.domain.Credentials_;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.domain.hibernate.HibernateUser_;
import com.noirix.repository.HibernateUserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@Log4j2
public class HibernateUserRepositoryImpl implements HibernateUserRepository {

//    private SessionFactory sessionFactory;
//
//    public HibernateUserRepositoryImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    private EntityManager entityManager;

    public HibernateUserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<HibernateUser> findAll() {
//        try (Session session = sessionFactory.openSession()) {
//
//
//            /*1. Change table name to mapped Entity: m_users -> HibernateUser u*/
//            /*2. Change table column names to mapped Entity fields:
//                  select * from m_users
//                  select u from HibernateUser u
//
//                  select id, name, birth_date from m_users
//                  select u.id, u.name, u.birthDate from HibernateUser u
//                  */
//
//            String hqlQuery =
//                    //"from HibernateUser";
//                    "select u from HibernateUser u";
//            //"select u.id, role.roleName from HibernateUser u left join u.role as role";
//            //"";
//            //"";
//            //"";
//
//            return session.createQuery(hqlQuery, HibernateUser.class).list();
////
////            return Collections.singletonList(session.find(HibernateUser.class, 6L));
//        }
        return null;
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
    public Object testHql() {
//        try (Session session = sessionFactory.openSession()) {
//
//
//            /*1. Change table name to mapped Entity: m_users -> HibernateUser u*/
//            /*2. Change table column names to mapped Entity fields:
//                  select * from m_users
//                  select u from HibernateUser u
//
//                  select id, name, birth_date from m_users
//                  select u.id, u.name, u.birthDate from HibernateUser u
//                  */
//
//            String hqlQuery =
//                    //"from HibernateUser";
//                    //"select u from HibernateUser u";
//                    "select u.id, role.roleName, u.weight from HibernateUser u left join u.role as role " +
////                            " " +
//                            "where role.roleName = com.noirix.domain.SystemRoles.ROLE_ADMIN " +
//                            "and u.weight > (select avg(u.weight) from HibernateUser u) " +
//                            "and u.id in(45, 46, 42) " +
//                            "and u.name like '%o%' " +
////                            "having u.weight > avg(u.weight) " +
//                            "";
//            //"";
//            //"";
//            //"";
//
//            return session.createQuery(hqlQuery).list();
////
////            return Collections.singletonList(session.find(HibernateUser.class, 6L));
//        }
        return null;
    }

    @Override
    public List<HibernateUser> testCriteriaApi(SearchCriteria criteria) {

        //1. Get Builder for Criteria object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HibernateUser> query = cb.createQuery(HibernateUser.class); //here select, where, orderBy, having
        Root<HibernateUser> root = query.from(HibernateUser.class); //here params  select * from m_users -> mapping

        /*type of future params in prepared statement*/
        ParameterExpression<String> param = cb.parameter(String.class);
        ParameterExpression<Long> userSearchParam = cb.parameter(Long.class);

        /*Provide access to fields in class that mapped to columns*/
        Expression<Long> id = root.get(HibernateUser_.id);
        Expression<String> name = root.get(HibernateUser_.name);
        Expression<String> surname = root.get(HibernateUser_.surname);
        //Expression<String> password = root.get(HibernateUser_.credentials).get(Credentials_.password);

        /*SQL Query customizing*/
        query.select(root)
                .distinct(true)
                .where(
                        cb.or(
                                cb.like(name, param),  //userName like param
                                cb.like(surname, param)  //userSurname like param
                        ),
                        cb.and(
                                cb.gt(root.get(HibernateUser_.id), userSearchParam), //>0
                                cb.not(id.in(40L, 50L)) //in (40,50)
                        )
//                        ,
//                        cb.between(
//                                root.get(TestUser_.birthDate),
//                                new Timestamp(new Date().getTime()),
//                                new Timestamp(new Date().getTime())
//                        )
                )
                .orderBy(cb.asc(root.get(HibernateUser_.id)));

        TypedQuery<HibernateUser> resultQuery = entityManager.createQuery(query); //prepared statement on hql
        resultQuery.setParameter(param, StringUtils.join("%", criteria.getQuery(), "%"));
        resultQuery.setParameter(userSearchParam, criteria.getUserLowerId());
        return resultQuery.getResultList();
    }

    @Override
    public HibernateUser save(HibernateUser object) {
//        try (Session session = sessionFactory.openSession()) {
//            session.saveOrUpdate(object);
//            return object;
//        }
        return null;
    }

    @Override
    public HibernateUser findById(Long key) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.find(HibernateUser.class, key);
//        }
        return null;
    }

    @Override
    public Optional<HibernateUser> findOne(Long key) {
        return Optional.of(findById(key));
    }

    @Override
    public HibernateUser update(HibernateUser object) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.getTransaction();
//            transaction.begin();
//            session.saveOrUpdate(object);
//            transaction.commit();
//            return object;
//        }
        return null;
    }

    @Override
    public Long delete(HibernateUser object) {
//        try (Session session = sessionFactory.openSession()) {
//            session.delete(object);
//            return object.getId();
//        }

        return null;
    }
}
