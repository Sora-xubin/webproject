package com.demo.demo.Service;

import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    // 只查询没有删除的图书
    public Page<Project> findAll(Pageable pageable) {
    	Example<Project> example = getNotDeleteExample();
    	return projectDao.findAll(example, pageable);
    }
    
    private Example<Project> getNotDeleteExample() {
        Project project = new Project();
        project.setState(2);
        return Example.of(project);
    }
}
