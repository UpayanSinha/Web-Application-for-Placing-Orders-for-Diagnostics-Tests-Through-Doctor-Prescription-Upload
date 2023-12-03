package com.diag.dao;

import com.diag.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email= :email")
    public User getUserByUserEmail(@Param("email")String email);
}
