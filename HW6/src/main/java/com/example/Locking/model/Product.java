package com.example.Locking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;

    private Long price;
}

/* The @version annotation assumes a column in the database exists to
*  represent the field that the annotation is tagged to.
*  JPA takes care of incrementing the version as well as all the version
*  checking upon save.
*
*  We must not manually update or increment version by ourselves
* */
