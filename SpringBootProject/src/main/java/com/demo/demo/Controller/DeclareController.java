package com.demo.demo.Controller;

import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.ProjectMember;
import com.demo.demo.Entity.ProjectRule;
import com.demo.demo.Entity.User;
import com.demo.demo.Service.DeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    @RequestMapping(value = "/setrule")
    @ResponseBody
    public String setRule(@RequestBody Map<String,Object> map){
        ProjectRule projectRule = new ProjectRule();
        projectRule.setRule((String) map.get("rule"));
        declareService.saveRule(projectRule);
        return "success!";
    }

    /**
     * 跳转规则页面
     */
    @GetMapping(value = "/project_rule")
    public String whachRule(Model model){
        model.addAttribute("rule",declareService.findRule());
        return "/declare/project_rule";
    }

    /**
     * 从规则跳转报名页面
     */
    @GetMapping(value = "/project_declare")
    public String declareEntrance(){
        return "/declare/project_declare";
    }

    /**
     * 创建申报项目
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
     * 已申报项目列表
     */
    @GetMapping(value = "/declare_project_list")
    public String findProject(Model model,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("datas", declareService.findDeclareProject(page, size,0));
        return "/declare/declare_project_list";
    }

    /**
     * 初审
     */
    @RequestMapping(value = "/firexamine")
    @ResponseBody
    public String firstExamine(@RequestBody Map<String,Integer>map){
        Project project = new Project();
        project = declareService.findProject( map.get("code"));
        declareService.firstExamine(project, map.get("state"));
        return "success";
    }

    /**
     * 给项目分配专家
     */
    @RequestMapping(value = "/disexpert")
    @ResponseBody
    public String distributionExpert(@RequestBody Map<String,String> map){
        Integer expertCode = Integer.parseInt(map.get("expertcode")) ;
        Integer projectCode = Integer.parseInt(map.get("projectcode"));
        declareService.distributionExpert(projectCode,expertCode);
        Project project = new Project();
        project = declareService.findProject(projectCode);
        declareService.setupProject(project);
        return "success!";
    }

    /**
     * 分配专家时，查看专家
     */
    @GetMapping(value = "/expert_list")
    public String findExpert(Model model,
                             @RequestParam(value = "projectcode")String projectCode,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("projectCode",Integer.parseInt(projectCode));
        model.addAttribute("experts",declareService.findExpertList(page, size,0));
        return "declare/expert_list";
    }

    /**
     * 专家查看需要自己待审核项目
     */
    @GetMapping(value = "/examineproject")
    public String examineProject(Model model,
                                 @RequestParam(value = "expertCode")int expertCode,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("expert",declareService.findExamineProject(page,size,expertCode));//需要从前端返回登录专家的code
        return "/declare/examine_project_list";
    }

    /**
     * 专家给项目评审
     */
    @RequestMapping(value = "/subcomment")
    @ResponseBody
    public String subComment(@RequestBody Map<String,Object> map, HttpSession session){
        User user =(User) session.getAttribute("user");
        declareService.subComment((Integer)map.get("projectcode"),user.getCode(),(Integer)map.get("mark"),(String)map.get("comment"));
        declareService.setupProjectFinish(declareService.findProject((Integer)map.get("projectcode")));
        return "success!";
    }

    /**
     * 查看完成审核项目
     */

    @GetMapping(value = "/examinefinsh_project_list")
    public String finishExamineProject(Model model,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("projects", declareService.findDeclareProject(page, size,4));
        return "declare/examineEnd_project_list";

    }

    /**
     * 查看评审
     */
    @GetMapping(value = "/project_comment_list")
    public String findComment(Model model,
                              @RequestParam(value = "projectCode")Integer projectCode,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "limit", defaultValue = "5") Integer size){
        model.addAttribute("comment",declareService.findComment(page,size,projectCode));//需要前端传回项目code
        model.addAttribute("projectcode",projectCode);
        return "declare/project_comment_list";
    }

    /**
     * 确定立项
     */
    @RequestMapping(value = "/setProject")
    @ResponseBody
    public String setDeclare(@RequestBody Map<String,Object>map){
        declareService.setDeclare(declareService.findProject(Integer.parseInt((String) map.get("projectcode"))),Integer.parseInt((String)map.get("state")));
        return "success!";
    }
}
