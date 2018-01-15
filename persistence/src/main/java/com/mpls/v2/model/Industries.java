package com.mpls.v2.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Industries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String image;

    @OneToMany(mappedBy = "industries")
    private List<Blog> blogList;

}
