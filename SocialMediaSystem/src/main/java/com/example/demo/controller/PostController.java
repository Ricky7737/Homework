package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //列出所有留言，對應 GET
    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> getALLPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // 模擬用戶新增留言，尾巴帶一個參數模擬使用者新增貼文
    @PostMapping("/addpost")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        // 假設新增成功
        Post newPost = postService.addPost(post);
        // 在後端上印出新增貼文成功
        System.out.println("新增貼文成功：");
        // 回傳新增的貼文
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
