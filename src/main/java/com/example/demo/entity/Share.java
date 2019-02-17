package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_id", nullable = false)
    private Long shareId;

    @Column
    private String country;

    @Column
    private String name;

    @Column
    private String colloge;

    @Column(nullable = false)
    private Date time;

    @Column
    private Double language_grade;

    @Column
    private Double grade;

    @Column
    private String background;

    @Column
    private String school;

    @Column
    private String s_colloge;

    @Column
    private String pic;

    @Column
    private String url;

}
