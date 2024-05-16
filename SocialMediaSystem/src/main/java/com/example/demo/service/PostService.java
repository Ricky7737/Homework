package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;

public interface PostService {
    // 取得所有文章
    List<Post> getAllPosts();
}
