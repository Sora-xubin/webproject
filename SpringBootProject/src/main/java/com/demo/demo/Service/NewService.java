package com.demo.demo.Service;

import com.demo.demo.Dao.NewDao;
import com.demo.demo.Dao.ProjectDao;
import com.demo.demo.Dao.ProjectMemberDao;
import com.demo.demo.Entity.New;
import com.demo.demo.Entity.Project;
import com.demo.demo.Entity.ProjectMember;
import com.demo.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by XB on 2018/12/17.
 */
@Service
public class NewService {
    @Autowired
    private NewDao newDao;
    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private ProjectDao projectDao;

    /**
     * 添加记录
     */
    public void saveNew(int projectCode,int state){
        List<ProjectMember> list = projectMemberDao.findAllByProjectcode(projectCode);
        Project project = projectDao.findByCode(projectCode);
        String str = project.getName();
        switch (state){
            case -2 :str +="立项未通过";
                break;
            case -1 :str +="初审未通过";
                break;
            case 0 :str +="已申报项目";
                break;
            case 1 :str +="通过初审";
                break;
            case 2 :str +="专家审核中";
                break;
            case  3:str +="审核完成";
                break;
            case  4:str +="立项成功";
                break;
            case  5:str +="中期材料待提交";
                break;
            case  6:str +="中期材料已提交";
                break;
            case  7:str +="中期检查通过";
                break;
            case  8:str +="中期检查待整改";
                break;
            case  9:str +="后期材料待提交";
                break;
            case 10 :str +="后期材料已提交";
                break;
            case  11:str +="后期材料待整改";
                break;
            case  12:str +="已结题";
                break;
        }
        for(ProjectMember projectMember : list){
            New news = new New();
            news.setUsercode(projectMember.getUsercode());
            news.setNews(str);
            newDao.save(news);
        }

    }

    /**
     * 查看历史纪录
     */
    public Page<New> history(Integer page, Integer size,Integer usercode){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<New> newsPage = newDao.findAllByUsercode(new Specification<New>() {
            @Override
            public Predicate toPredicate(Root<New> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("usercode"), usercode);
            }
        },pageable);
        return newsPage;
    }
}
