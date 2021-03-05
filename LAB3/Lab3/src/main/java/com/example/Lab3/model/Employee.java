package com.example.Lab3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "employee_info")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "employee")
public class Employee {

    @Id
    private int id;

    private Name name;

    @Column(name = "employee_age")
    private int age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Address> addresses;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Benefit> benefits;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Leave> leaves;

    @Transient
    private String something_we_do_not_put_into_object;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    @MapsId
    private User user;

}
