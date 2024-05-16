package com.example.demo.service.Impl;

import com.example.demo.dao.PostDao;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    // 實作 getAllPosts()
    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }
}
