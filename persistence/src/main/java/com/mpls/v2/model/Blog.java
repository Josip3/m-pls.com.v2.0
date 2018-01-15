package com.mpls.v2.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String shortDescr;

    private String header;

    private String mainText;

    private String image;

    private LocalDateTime date;

    @ManyToOne
    private Industries industries;


}
