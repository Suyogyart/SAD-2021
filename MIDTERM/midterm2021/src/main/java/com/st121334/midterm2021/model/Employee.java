package com.st121334.midterm2021.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Level level;

    private LocalDate birthday;

    @Transient
    private Date birthday_;

    @Transient
    private Monetary baseSalary;

    //10 digits precision and 2 digits after decimal
    @Column(name="base_salary", precision=10, scale=2)
    private BigDecimal baseSalary_;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Address> addresses;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    @MapsId
    private User user;

    public void addAddress(Address address) {
        if (this.getAddresses() == null) {
            this.setAddresses(List.of(address));
        } else {
            this.getAddresses().add(address);
        }
    }

    public void addUser(User user) {
        this.setUser(user);
    }

}
