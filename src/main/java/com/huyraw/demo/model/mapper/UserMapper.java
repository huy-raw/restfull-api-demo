package com.huyraw.demo.model.mapper;


import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "name")
    User toUser(UserDTO userDTO);


    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);


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
