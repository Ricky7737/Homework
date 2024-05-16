package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDAO {
    // 儲存學生資料的地方
    User registerUser(User user);
    User findByPhoneNumber(String phoneNumber);
}
