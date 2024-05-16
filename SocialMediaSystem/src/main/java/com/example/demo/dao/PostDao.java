package com.example.demo.dao;

import com.example.demo.model.Post;
import java.util.List;


public interface PostDao {
    // 取得所有文章
    List<Post> getAllPosts();
}
