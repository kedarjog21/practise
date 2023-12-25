package com.example.practise.repository;

import com.example.practise.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogsRepository extends JpaRepository<Blog, Long> {
}
