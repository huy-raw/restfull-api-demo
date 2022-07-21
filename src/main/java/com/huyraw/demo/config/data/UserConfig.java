package com.huyraw.demo.config.data;

import com.huyraw.demo.config.security.PasswordEncoder;
import com.huyraw.demo.entity.User;
import com.huyraw.demo.repository.UserRepository;
import com.huyraw.demo.util.constants.UserRole;
import com.huyraw.demo.util.constants.UserStatus;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@AllArgsConstructor
public class UserConfig {

    private final PasswordEncoder passwordEncoder;
    @Bean(name = "User")
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User user1 = new
                    User("Huy Raw", passwordEncoder.encode("111111111"), "huyraw@gmail.com", LocalDate.of(2001, 9, 19), UserStatus.ACTIVE, UserRole.ADMIN);
            User user2 = new
                    User("User2", passwordEncoder.encode("111111111"), "user2@gmail.com", LocalDate.of(2001, 5, 1), UserStatus.ACTIVE, UserRole.USER);
            User user3 = new
                    User("User1", passwordEncoder.encode("111111111"), "user3@gmail.com", LocalDate.of(2001, 6, 9), UserStatus.ACTIVE, UserRole.USER);
            User user4 = new
                    User("User1", passwordEncoder.encode("111111111"), "user4@gmail.com", LocalDate.of(2001, 2, 25), UserStatus.INACTIVE, UserRole.USER);
            User user5 = new
                    User("User1", passwordEncoder.encode("111111111"), "user5@gmail.com", LocalDate.of(2001, 1, 19), UserStatus.ACTIVE, UserRole.USER);

            repository.saveAll(List.of(user1,user2,user3,user4,user5));
        };
    }
}
