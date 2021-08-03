package com.java.forum.service;

import com.java.forum.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;
    public AlphaService(){
        System.out.println("实例化");
    }

    @PostConstruct
    public  void init(){
        System.out.println("Initializing");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁");
    }

    public String find(){
        return alphaDao.select();
    }


}
