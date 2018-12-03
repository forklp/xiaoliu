package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_account", nullable = false, length = 20,unique = true)
    private String userAccount;

    @Column(name = "user_password", nullable = false, length = 20)
    private String userPassword;

    @Column(name = "user_code", nullable = false)
    private String userCode;

    //积分
    @Column(name = "user_point",nullable = false)
    private int userPoint;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Article> articleList;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Comment> commentList;

}
