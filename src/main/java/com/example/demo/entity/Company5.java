package com.example.demo.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Company5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String projectType;

    private String projectSpend;

    @Column(nullable = false)
    private String studentType;

    @Column(nullable = false)
    private int projectLength;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private Date projectTime;

    @Column(nullable = false)
    private String projectAddress;

    @Column(nullable = false)
    private String projectDescription;

//    @Override
//    public boolean equals(Object obj) {
//        Company5 company5 = (Company5) obj;
//        return companyId.equals(company5.getCompanyId());
//    }
//
//    @Override
//    public int hashCode() {
//        return companyId.hashCode();
//    }

}
