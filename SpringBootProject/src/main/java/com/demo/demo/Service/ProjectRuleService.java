package com.demo.demo.Service;

import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Dao.ProjectRuleDao;
import com.demo.demo.Entity.ProjectRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by XB on 2018/12/17.
 */
@Service
public class ProjectRuleService {
    @Autowired
    private ProjectRuleDao projectRuleDao;

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
}
