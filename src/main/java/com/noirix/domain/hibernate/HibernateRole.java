package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.noirix.domain.SystemRoles;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "m_roles")
public class HibernateRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private SystemRoles roleName;

    //@ManyToOne
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private HibernateUser user;

    public HibernateRole() {
    }

    public HibernateRole(Long id, SystemRoles roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public HibernateRole(SystemRoles roleName, HibernateUser user) {
        this.roleName = roleName;
        this.user = user;
    }
}
