package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Company4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "company_url", nullable = false)
    private String companyUrl;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String companyTitle;
}
