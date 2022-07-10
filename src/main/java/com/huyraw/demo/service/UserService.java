package com.huyraw.demo.service;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.UserMapper;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.model.mapper.request.UpdateUserRequest;
import com.huyraw.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUser() {
        List<User> list = userRepository.findAll();
        UserDTO userDTO;
        List<UserDTO> listUserDTO = new ArrayList<>();

        if (list.isEmpty()) {
            log.error("Not found any users");
            throw new IllegalStateException("Not found any users");
        }

        for (User user : list) {
            userDTO = userMapper.INSTANCE.toUserDTO(user);
            listUserDTO.add(userDTO);
        }
        log.info(String.format("Get %d users", (long) list.size()));
        return listUserDTO;
    }

    public void createUser(@NotNull @Valid CreateUserRequest req) {
        Optional<User> user = userRepository.findUserByEmail(req.getEmail());

        try {
            if (user.isPresent()) {
                log.info("Use already exist");
                throw new IllegalStateException("User exist");
            }
            User user_ = userMapper.INSTANCE.createUser(req);
            userRepository.save(user_);

        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUserById(@NotNull @Valid String id) {
        Optional<User> userOptional = userRepository.findById(id);
        try {
            if (!userOptional.isPresent()) {
                log.error("User is have id " + id + " does not existed");
                throw new IllegalStateException("User is have id " + id + " does not existed");
            }
            userRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public UserDTO findUserById(@NotNull @Valid String id) {
       return userMapper.INSTANCE.toUserDTO(userRepository.findById(id).orElseThrow(() -> {
            log.error("User is have id " + id + " doesn't existed");
            throw new IllegalStateException("User is have id " + id + " doesn't existed");
        }));

    }

    public UserDTO findUserByEmail(@NotNull @Email String email) {
        return userMapper.INSTANCE.toUserDTO(userRepository.findUserByEmail(email).orElseThrow(() ->{
            log.error("User is have email " + email + " doesn't existed");
            throw new IllegalStateException("User is have email " + email + " doesn't existed");
        }));
    }

    public UserDTO updateUser(@NotNull @Valid String id, UpdateUserRequest req){
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("User is have id %s doesn't existed", id));
            throw new IllegalStateException("User is have id " + id + " doesn't existed");
        });
        try {
            user = userRepository.save(userMapper.updateUser(req, user));
            log.info(String.format("Update user have id: %s", id));
        } catch (NullPointerException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return userMapper.INSTANCE.toUserDTO(user);
    }


    //    public List<UserDTO> getUsersLimit(int limit){
//        String query = "SELECT s FROM User s ORDER BY s.id";
//        UserDTO userDTO;
//
//        List<User> listUser = userRepository.findAll()
//        if(!listUser.isEmpty()){
//           for(User user : listUser){
//               userDTO = userMapper.INSTANCE.toUserDTO(user);
//               listUserDTO.add(userDTO);
//           }
//            log.info(String.format("Get %d users", (long) listUserDTO.size()));
//           return listUserDTO;
//        }
//        log.error("Not found any users");
//        return null;
//    }


}
