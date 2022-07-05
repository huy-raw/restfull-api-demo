package com.huyraw.demo.service;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.UserMapper;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final List<UserDTO> listUserDTO;

    public List<UserDTO> getAllUser() {
        List<User> list = userRepository.findAll();
        UserDTO userDTO;


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

    public void createUser(CreateUserRequest req) {
        Optional<User> userOptional = userRepository.findUserByEmail(req.getEmail());

        try {
            if (userOptional.isPresent()) {
                log.info("Use already exist");
                throw new IllegalStateException("User exist");
            }
            User user = userMapper.INSTANCE.createUser(req);
            userRepository.save(user);

        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
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
