package com.example.demo.controller;

import com.example.demo.entity.Company5;
import com.example.demo.repository.*;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    Company5Repository company5Repository;


    //优质机构
    @PostMapping("/1")
    public Object getCompany1(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company1Repository.findAll(pageable).getContent();
    }

    //名师讲堂
    @PostMapping("/2")
    public Object getCompany2(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company2Repository.findAll(pageable).getContent();
    }

    //成功案例
    @PostMapping("/3")
    public Object getCompany3(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company3Repository.findAll(pageable).getContent();
    }

    //作品展示
    @PostMapping("/4")
    public Object getCompany4(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company4Repository.findAll(pageable).getContent();
    }

    //提升
    @PostMapping("/search")
    public Object getCompany5(@RequestParam String country, @RequestParam String type, @RequestParam int length, @RequestParam String time, @RequestParam int page, @RequestParam int size) {
        List<Company5> list1 = new ArrayList<>();
        List<Company5> list2 = new ArrayList<>();
        List<Company5> list3 = new ArrayList<>();
        List<Company5> list4 = new ArrayList<>();
        List<Company5> finalList = company5Repository.findAll();
        Date date = DateUtil.dateformate(time);
        if (!country.equals("null")) {
            list1 = company5Repository.findByCountry(country);
            finalList.retainAll(list1);
        }
        if (!type.equals("null")) {
            list2 = company5Repository.findByProjectType(type);
            finalList.retainAll(list2);
        }
        if (length != 0) {
            list3 = company5Repository.findByProjectLengthLessThanEqual(length);
            finalList.retainAll(list3);
        }
        if (date != null) {
            list4 = company5Repository.findByProjectTimeAfter(date);
            System.out.println(list4.size());
            finalList.retainAll(list4);
        }

        ArrayList returnList = new ArrayList(finalList.subList(page*size, page*size+size-1));
        return returnList;
    }

    @PostMapping("/promote")
    public Object getPromote(@RequestParam String type, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "companyId");
        return  company5Repository.findByProjectType(type, pageable).getContent();
    }

}
