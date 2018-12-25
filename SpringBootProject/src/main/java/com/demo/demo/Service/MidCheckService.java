package com.demo.demo.Service;

import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MidCheckService {
    @Autowired
    ProjectDao projectDao;

    /**
     * 获取已经立项的项目列表
     */
    public Page<Project> getApprovedProject(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Project> projectPage = projectDao.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("state"), 2);
            }
        } ,pageable);
        return projectPage;
    }

    /**
     *
     * @param id
     * @param comment
     * @param date
     * @return
     * @throws ParseException
     */
    public boolean updateProject(Integer id, String comment, String date) throws ParseException {
        Project demo = new Project();
        demo.setId(id);
        demo.setState(3);
        demo.setComment(comment);
        demo.setMidtime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()));
        projectDao.save(demo);
        return true;
    }

    public Project findProjectByCode(int projectCode) {
        return projectDao.findByCode(projectCode);
    }
}
