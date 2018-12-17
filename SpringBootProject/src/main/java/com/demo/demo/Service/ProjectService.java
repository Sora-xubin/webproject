package com.demo.demo.Service;

import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XB on 2018/12/17.
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectDao projectDao;

    /**
     * 提交项目
     */
    public void saveProject(Project project){
        projectDao.save(project);
    }

    /**
     *
     */
}
