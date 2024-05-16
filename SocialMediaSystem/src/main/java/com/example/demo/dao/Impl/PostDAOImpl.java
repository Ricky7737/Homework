package com.example.demo.dao.Impl;

import com.example.demo.dao.PostDao;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class PostDAOImpl implements PostDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 讀取所有的貼文
    @Override
    public List<Post> getAllPosts() {
        String sql = "SELECT * FROM post";

        // 執行查詢，並將結果轉換成 Post 物件
        // 注意：此處的 query() 兩個參數代表：SQL 語法、回傳值轉換函式
        // (resultSet, rowNum) -> { 回傳值轉換函式 }  Lambda 語法
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Post post = new Post();
            post.setPostId(resultSet.getInt("PostID"));
            post.setUserId(resultSet.getInt("UserID"));
            post.setContent(resultSet.getString("Content"));
            post.setImage(resultSet.getString("Image"));
            post.setPublishDate(resultSet.getTimestamp("PublishTime"));
            return post;
        });
    }
}

