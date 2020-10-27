package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*Here we will store PK of m_users table*/
    private Long id;

    private String name;

    private String surname;

    private Date birthDate;

    private Gender gender = Gender.NOT_SELECTED;

    private Timestamp created = new Timestamp(System.currentTimeMillis());

    private Timestamp changed = new Timestamp(System.currentTimeMillis());

    private Float weight;

//    @Autowired
//    @Qualifier("car1")

    //JSR-330
    //@Inject
    //@Named("car1")
    private Car userCar;

//    @Autowired
//    private void setUserCar(Car userCar) {
//        this.userCar = userCar;
//    }

    @Autowired
    public User(Car userCar) {
        this.userCar = userCar;
    }

    public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
