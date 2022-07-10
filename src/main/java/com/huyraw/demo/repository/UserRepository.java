package com.huyraw.demo.repository;

import com.huyraw.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Query(value = "" + "SELECT s FROM User s WHERE s.email LIKE  ?1" +"")
    Optional<User> findUserByEmail(String email);


    @Query(value = "" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " + "" +
            "TRUE ELSE FALSE END " + "" +
            "FROM User s WHERE s.email LIKE ?1" + "")
    Boolean isExistUserByEmail(String email);


}
