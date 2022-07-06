package com.huyraw.demo.model.mapper;


import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.model.mapper.request.UpdateUserRequest;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.Nullable;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "name")
    User toUser(UserDTO userDTO);


    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);


    default User updateUser(@Nullable UpdateUserRequest req, User user) {

        if (!req.getName().isEmpty()
                && !req.getName().equalsIgnoreCase(user.getFullName())
                && req.getEmail() != null) {
            user.setFullName(req.getName());
        }


        if (!req.getEmail().isEmpty()
                && !req.getEmail().equalsIgnoreCase(user.getEmail())
                && req.getEmail() != null) {
            user.setEmail(req.getEmail());
        }


        if (!req.getDob().equals(user.getDob())
                && req.getEmail() != null) {
            user.setDob(req.getDob());
        }
        return user;

    }

    default User createUser(CreateUserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setFullName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setDob(request.getDob());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        return user;

    }
}
