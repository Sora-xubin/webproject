package com.demo.demo.Service;

import com.demo.demo.Dao.*;
import com.demo.demo.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XB on 2018/12/18.
 */
@Service
public class DeclareService {
    @Autowired
    private ProjectRuleDao projectRuleDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProjectMemberDao projectMemberDao;
    /**
     * 设置规则
     */
    public void saveRule(ProjectRule projectRule){
        projectRuleDao.save(projectRule);
    }

    /**
     * 查看规则
     */
    public ProjectRule findRule(){
        return projectRuleDao.findFirstByOrderById();
    }

    /**
     * 提交项目
     */
    public Project saveProject(Project project){
        return projectDao.save(project);
    }

    /**
     * 保存成员
     */
    public void saveMember(ProjectMember projectMember){
        projectMemberDao.save(projectMember);
    }

    /**
     * 查看所有申报项目
     * 申报项目0
     */
    public List<Project> findProjectbyState(int state){
        return projectDao.findAllByState(state);
    }

    /**
     * 查看单个项目
     */
    public Project findProject(int code){
        return projectDao.findByCode(code);
    }

    /**
     * 提交评语
     */
    public void subComment(int projectCode,int expertCode,int mark,String comment){
        Comment comment1 = commentDao.findByProjectcodeAndExpertcode(projectCode,expertCode);
        comment1.setMark(mark);
        comment1.setComent(comment);
        commentDao.save(comment1);
    }

    /**
     * 初审
     * 1=初审通过
     * -1=未通过
     */
    public void firstExamine(Project project,int state){
        project.setState(state);
        projectDao.save(project);
    }

    /**
     * 查看专家列表
     * 专家角色编码2
     */
    public List<User> findExpertList(){
        return userDao.findAllByRolecode(2);
    }
    /**
     * 分配专家
     */
    public void distributionExpert(int code,int expertcode){
        Comment comment = new Comment();
        comment.setProjectcode(code);
        comment.setExpertcode(expertcode);
        commentDao.save(comment);
    }

    /**
     * 2=专家审核中
     */
    public void setupProject(Project project){
        project.setState(2);
        projectDao.save(project);
    }

    /**
     * 专家查看自己待审核项目列表
     */
    public List<Project> findExamineProject(int expertCode){
        List<Comment> list = commentDao.findAllByExpertcode(expertCode);
        List<Project> list1 = new ArrayList<>();
        for (Comment comment : list){
            if(null != comment.getComent()){
                list1.add(projectDao.findByCode(comment.getProjectcode()));
            }
        }
        return list1;
    }

    /**
     * 立项评审完成
     * 3=立项评审完成
     */
    public void setupProjectFinish(Project project){
        List<Comment> list = new ArrayList<>();
        list = commentDao.findAllByProjectcode(project.getCode());
        for (int i = 0;i <list.size();i++){
            if (list.get(i).getComent() == null){
                break;
            }
            if (i == list.size()-1){
                project.setState(3);
                projectDao.save(project);
            }
        }
    }

    /**
     * 查看所有评语
     */
    public List<Comment> findComment(int projectCode){
        return commentDao.findAllByProjectcode(projectCode);
    }

    /**
     * 初审结果
     * 4=已立项
     * 5=不立项
     */
    public void setDeclare(Project project,int state){
        if(state == 1){
            project.setState(4);
        }else {
            project.setState(5);
        }
        projectDao.save(project);
    }

}
