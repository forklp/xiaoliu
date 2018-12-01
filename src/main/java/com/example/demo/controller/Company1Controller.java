package com.example.demo.controller;

import com.example.demo.repository.Company1Repository;
import com.example.demo.repository.Company2Repository;
import com.example.demo.repository.Company3Repository;
import com.example.demo.repository.Company4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class Company1Controller {
    @Autowired
    Company1Repository company1Repository;

    @Autowired
    Company2Repository company2Repository;

    @Autowired
    Company3Repository company3Repository;

    @Autowired
    Company4Repository company4Repository;

    @PostMapping("/1")
    public Object getCompany1(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company1Repository.findAll(pageable).getContent();
    }

    @PostMapping("/2")
    public Object getCompany2(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company2Repository.findAll(pageable).getContent();
    }

    @PostMapping("/3")
    public Object getCompany3(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company3Repository.findAll(pageable).getContent();
    }

    @PostMapping("/4")
    public Object getCompany4(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company4Repository.findAll(pageable).getContent();
    }

}
