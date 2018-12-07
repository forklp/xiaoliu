package com.example.demo.repository;

import com.example.demo.entity.Company5;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface Company5Repository extends JpaRepository<Company5, Long> {
    List<Company5> findByCountry(String country);
    List<Company5> findByProjectType(String type);
    List<Company5> findByProjectTimeAfter(Date time);
    List<Company5> findByProjectLengthLessThanEqual(int length);
}
