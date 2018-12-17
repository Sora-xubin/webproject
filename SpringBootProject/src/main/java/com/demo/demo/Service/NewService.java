package com.demo.demo.Service;

import com.demo.demo.Dao.NewDao;
import com.demo.demo.Entity.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XB on 2018/12/17.
 */
@Service
public class NewService {
    @Autowired
    private NewDao newDao;

    /**
     * 添加记录
     */
    public void saveNew(New news){
        newDao.save(news);
    }

    /**
     * 查看历史纪录
     */
    public List<New> history(Integer usercode){
        return newDao.findAllByUsercode(usercode);
    }
}
