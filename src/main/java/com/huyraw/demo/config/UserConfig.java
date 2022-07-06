package com.huyraw.demo.config;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.repository.UserRepository;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean(name = "User")
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User user1 = new
                    User("Huy Raw", "111111111", "huyraw@gmail.com", LocalDate.of(2001, 9, 19), UserStatus.ACTIVE, UserRole.ADMIN);
            User user2 = new
                    User("User2", "111111111", "user2@gmail.com", LocalDate.of(2001, 5, 1), UserStatus.ACTIVE, UserRole.USER);
            User user3 = new
                    User("User1", "111111111", "user3@gmail.com", LocalDate.of(2001, 6, 9), UserStatus.ACTIVE, UserRole.USER);
            User user4 = new
                    User("User1", "111111111", "user4@gmail.com", LocalDate.of(2001, 2, 25), UserStatus.INACTIVE, UserRole.USER);
            User user5 = new
                    User("User1", "111111111", "user5@gmail.com", LocalDate.of(2001, 1, 19), UserStatus.ACTIVE, UserRole.USER);

            repository.saveAll(List.of(user1,user2,user3,user4,user5));
        };
    }
}
