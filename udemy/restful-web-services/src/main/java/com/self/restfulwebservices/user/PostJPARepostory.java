package com.self.restfulwebservices.user;


import com.self.restfulwebservices.user.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostJPARepostory extends JpaRepository<Post, Integer> {

}
