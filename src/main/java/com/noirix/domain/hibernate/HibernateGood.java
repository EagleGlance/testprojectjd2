package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "m_goods")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class HibernateGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @ManyToMany
    @JoinTable(name = "l_user_goods",
            joinColumns = @JoinColumn(name = "good_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("goods")
    private Set<HibernateUser> users = Collections.emptySet();
}
