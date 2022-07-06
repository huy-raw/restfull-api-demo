package com.huyraw.demo.repository;

import com.huyraw.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface UserRepository  extends JpaRepository<User, String> {


    @Query("SELECT s FROM User s WHERE s.email like ?1")
    User findUserByEmail(String email);






}
