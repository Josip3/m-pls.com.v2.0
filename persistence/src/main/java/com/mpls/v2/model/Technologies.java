package com.mpls.v2.model;

import javax.persistence.*;

@Entity
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String image;

}
