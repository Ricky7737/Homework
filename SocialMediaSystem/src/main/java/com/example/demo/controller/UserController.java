package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    // 回傳 ResponseEntity<User>，@ResponseBody 會自動將 User 物件轉成 JSON 格式
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user); // 註冊使用者，使用 UserService 物件的 registerUser 方法
        System.out.println("有使用者註冊");
        return ResponseEntity.status(HttpStatus.CREATED).body(user); // 回傳 HTTP 201 CREATED 狀態碼，並回傳 User 物件
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.login(user); // 登入使用者，使用 UserService 物件的 login 方法
        System.out.println("有使用者登入");
        return ResponseEntity.status(HttpStatus.OK).body(user); // 回傳 HTTP 200 OK 狀態碼，並回傳 User 物件
    }
}
