package com.demo.demo.Service;

import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Dao.ProjectMemberDao;
import com.demo.demo.Dao.UserDao;
import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.ProjectMember;
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

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectMemberDao projectMemberDao;

    /**
     * 获取已经立项的项目列表
     */
    public Page<Project> getApprovedProject(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Project> projectPage = projectDao.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("state"), 2);
            }
        },pageable);
        return projectPage;
    }

    /**
     * 根据用户Code获取该用户的所有项目
     * @param page
     * @param size
     * @return
     */
    public Page<Project> getUserProject(Integer page, Integer size) {
        //根据userCode找到projectCode
        Integer userCode = 1;
        List<ProjectMember> projects = projectMemberDao.findByusercode(userCode);
        //根据projectCode进行分页查询
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Project> projectPage = projectDao.findAll(new Specification<Project>() {
            //List保存查询条件
            List<Predicate> list = new ArrayList<Predicate>();
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                for (int i = 0; i < projects.size(); i++) {
                    list.add(criteriaBuilder.equal(root.get("code"), projects.get(i).getProjectcode()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.or(list.toArray(p));
            }
        }, pageable);
        return projectPage;
    }

    /**
     * 设置中期材料说明
     * @param code
     * @param comment
     * @param date
     * @return
     * @throws ParseException
     */
    public boolean updateProject(Integer code, String comment, String date) throws ParseException {
        Project demo = new Project();
        demo.setCode(code);
        demo.setMidexplain(comment);
        demo.setMidtime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()));
        projectDao.save(demo);
        return true;
    }

    /**
     * 保存上传的中期材料地址
     * @param projectCode
     */
    public void saveMidAddress(int projectCode,String fileAddress){
        Project project = projectDao.findByCode(projectCode);
        project.setMidreport(fileAddress);
        projectDao.save(project);
    }
    /**
     * 查找上传的中期材料地址
     * @param projectCode
     */
    public String findMidAddress(int projectCode){
        Project project = projectDao.findByCode(projectCode);
        return project.getMidreport();
    }
    public Project findProjectByCode(int projectCode) {
        return projectDao.findByCode(projectCode);
    }
}
