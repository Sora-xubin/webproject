package com.demo.demo.Controller;

import com.demo.demo.Service.DeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by XB on 2018/12/20.
 */
@Controller
public class DeclareController {
    @Autowired
    DeclareService declareService;

    /**
     * 创建规则
     */
    @GetMapping(value = "/dechome/setrule")
    public String setRule(){
        return "/dechome";
    }

    /**
     * 创建项目
     */
    @GetMapping(value = "/submitproject")
    public String subProject(){
        return "/home";
    }

    /**
     * 项目列表
     */
    @GetMapping(value = "/dechome")
    public String findProject(){
        return "";
    }

    /**
     * 初审
     */
    @GetMapping(value = "/dechome/firexamine")
    public String firstExamine(){
        return "/dechome";
    }

    /**
     * 分配专家
     */
    @GetMapping(value = "/dechome/disexpert")
    public String distributionExpert(){
        return "";
    }

    /**
     * 立项评审
     */
    @GetMapping(value = "/dechome/setupproject")
    public String setupProject(){
        return "/dechome";
    }

    /**
     * 评审
     */
    @GetMapping(value = "subcomment")
    public String subComment(){
        return "/home";
    }

    /**
     * 查看评审
     */
    @GetMapping(value = "/dechome/findcomment")
    public String findComment(){
        return "";
    }

    /**
     * 确定立项
     */
    @GetMapping(value = "/dechome/findcomment/setdeclare")
    public String setDeclare(){
        return "/dechome";
    }
}
