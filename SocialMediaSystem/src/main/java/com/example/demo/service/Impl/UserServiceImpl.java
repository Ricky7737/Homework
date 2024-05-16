package com.example.demo.service.Impl;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(User user) {
        // 檢查是否有相同的使用者名稱
        if (userDao.findByPhoneNumber(user.getPhoneNumber()) != null) {
            throw new IllegalArgumentException("Phone number already exists"); // 若有相同的手機號碼，則拋出例外
        }
        // 若沒有相同的手機號碼，則進行註冊
        return userDao.registerUser(user);
    }

    @Override
    public User login(User user) {
        // 從資料庫中尋找使用者名稱是否存在
        User existingUser = userDao.findByPhoneNumber(user.getPhoneNumber());
        // 如果不存在該手機號碼或者密碼不正確，拋出運行時異常
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid phone number or password"); // 若不存在或密碼不正確，則拋出例外
        }
        return existingUser; // 返回現有的使用者資料
    }
}
