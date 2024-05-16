package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    // 註冊使用者
    User registerUser(User user);

    // 使用者登入
    User login(User user);
}
