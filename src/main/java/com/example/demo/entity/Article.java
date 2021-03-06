package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date time;

    @Column
    private String pic;

    //一共三种种类
    @Column
    private String type;

    @Column
    private String url;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "user_account")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Comment> commentList;
}

