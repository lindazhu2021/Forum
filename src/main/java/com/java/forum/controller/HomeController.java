package com.java.forum.controller;

import com.java.forum.entity.DiscussPost;
import com.java.forum.entity.Page;
import com.java.forum.entity.User;
import com.java.forum.service.DiscussPostService;
import com.java.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndex(Model model, Page page){
        //方法调用前，SpringMvc会自动实例化Model和Page,并将Page注入Model
        //所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows((discussPostService.findDiscussPostRows(0)));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list!=null){
            for (DiscussPost discussPost:list) {
                Map<String,Object> map = new HashMap<>();
                map.put("post",discussPost);
                User user = userService.findUserById(discussPost.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);


        return "/index";
    }
}
