package com.demo.demo.Controller;

import com.demo.demo.Entity.User;
import com.demo.demo.Service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by XB on 2019/1/3.
 */
@Controller
public class NewController {
    @Autowired
    NewService newService;

    @GetMapping(value = "/news")
    public String findNews(Model model,
                           HttpSession session,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "5") Integer size){
    	User user = (User)session.getAttribute("user");
    	Integer userCode = user.getCode();
        model.addAttribute("News",newService.history(page,size,userCode));
        return "new/new_list";
    }
}
