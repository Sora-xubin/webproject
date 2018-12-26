package com.demo.demo.Controller;

import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.ProjectMember;
import com.demo.demo.Service.DeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 跳转规则
     */
    @GetMapping(value = "/project_rule")
    public String whachRule(Model model){
        model.addAttribute("rule",declareService.findRule());
        return "/middle/project_rule";
    }

    /**
     * 跳转报名
     */
    @GetMapping(value = "/project_declare")
    public String declareEntrance(){
        return "/middle/project_declare";
    }

    /**
     * 创建项目
     */
    @RequestMapping(value = "/submitproject")
    @ResponseBody
    public String subProject(@RequestBody Map<String,Object> map){
        Project project = new Project();
        ProjectMember projectMember = new ProjectMember();
        System.out.println((String)map.get("name"));
        project.setName((String) map.get("name"));
        project.setLevel((String) map.get("level"));
        project.setPromise((String) map.get("promise"));
        project.setState(0);
        project.setCode(declareService.saveProject(project).getId());
        projectMember.setProjectcode(project.getCode());
        projectMember.setUsercode(Integer.parseInt((String) map.get("leader")));
        declareService.saveMember(projectMember);
        return "success!";
    }

    /**
     * 申报项目列表
     */
    @GetMapping(value = "/dechome")
    public String findProject(Model model){
        model.addAttribute("projectlist",declareService.findDeclareProject());
        return "/middle/declare_project_list";
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
     * 查看专家
     */
    @GetMapping(value = "/expert_list")
    public String findExpert(Model model){
        model.addAttribute("expert",declareService.findExpertList());
        return "/middle/expert_list";
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
