package com.example.demo.service;

import com.example.demo.entity.Share;
import com.example.demo.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShareService {
    @Value("${database.pagesize}")
    int pageSize;

    @Autowired
    ShareRepository shareRepository;

    public Object getshare(int page, String country){
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "shareId");
        List<Share> content = shareRepository.findAll(pageable).getContent();
        ArrayList<Share> tem = new ArrayList<>();
        for(Share share : content){
            if(share.getCountry().equals(country)){
                tem.add(share);
            }
        }
        return tem;
    }
}
