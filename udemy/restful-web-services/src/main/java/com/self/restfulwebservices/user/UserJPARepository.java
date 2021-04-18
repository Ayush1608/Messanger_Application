package com.self.restfulwebservices.user;


import java.util.List;

import com.self.restfulwebservices.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {

}
