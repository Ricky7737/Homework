package com.example.demo.dao.Impl;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class UserDaoImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User registerUser(User user) {
        String SQL = "INSERT INTO User (UserName, Email, Password, CoverImage, Phone) VALUES (?, ?, ?, ?, ?)";

        // 使用 KeyHolder 來獲取自動生成的 UserID
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, new String[]{"UserID"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getCoverImage());
            ps.setString(5, user.getPhoneNumber());
            return ps;
        }, keyHolder);

        // 從 KeyHolder 中獲取自動生成的 UserID
        if (keyHolder.getKey() != null) {
            user.setUserId((int) keyHolder.getKey().intValue());
        }

        return user;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        String SQL = "SELECT * FROM User WHERE Phone = ?";
        try {
            // 這串意思是，List user = 查詢資料庫，User 類別的物件，使用 phoneNumber 作為查詢條件
            // new Object[]{phoneNumber} 是指，查詢條件是 phoneNumber 這個值替換 ? 符號
            // new BeanPropertyRowMapper<>(User.class) 是指，將查詢結果轉換成 User 類別的物件
            List<User> users = jdbcTemplate.query(SQL, new Object[]{phoneNumber}, new BeanPropertyRowMapper<>(User.class));
            return users.isEmpty() ? null : users.get(0); // 若查詢結果為空，則回傳 null，否則回傳第一筆資料
        } catch (EmptyResultDataAccessException ex) {
            // 若查詢結果為空，則捕捉 EmptyResultDataAccessException 例外，並回傳 null
            return null;
        }
    }
}
