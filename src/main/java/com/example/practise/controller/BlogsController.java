package com.example.practise.controller;

import com.example.practise.model.Blog;
import com.example.practise.repository.BlogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogsController {
    BlogsRepository blogsRepository;

    // Create operation
    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogsRepository.save(blog);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    // Read operation
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> users = blogsRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        Optional<Blog> blogOptional = blogsRepository.findById((long)id);
        return blogOptional.map(blog -> new ResponseEntity<>(blog, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update operation
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateUser(@PathVariable int id, @RequestBody Blog updatedBlog) {
        Optional<Blog> blogOptional = blogsRepository.findById((long)id);

        return blogOptional.map(blog -> {
            blog.setTitle(updatedBlog.getTitle());
            blog.setBody(updatedBlog.getBody());
            Blog savedBlog = blogsRepository.save(blog);
            return new ResponseEntity<>(savedBlog, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<Blog> userOptional = blogsRepository.findById((long)id);
        return userOptional.map(blog -> {
            blogsRepository.delete(blog);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
