package com.example.demo.service;

import com.example.demo.core.service.BaseService;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件描述
 *
 * @author sujia
 * @date 2019年11月20日 16:01
 */
@Service
public class UserServiceImpl extends BaseService<User> {

    @Autowired
    private UserDao userDao;

    public User findById(Integer id) {
        return super.findById(id);
    }


    public int insertUser(User user) {
        try {
            return super.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
