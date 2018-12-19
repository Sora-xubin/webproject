package com.demo.demo.Service;

import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Dao.ProjectRuleDao;
import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.ProjectRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void saveProject(Project project){
        projectDao.save(project);
    }

    /**
     * 查看所有项目
     */
    public List<Project> findAllProject(){
        return projectDao.findAll();
    }

}
