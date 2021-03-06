package com.huyraw.demo.model.mapper;


import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.model.mapper.request.UpdateUserRequest;
import com.huyraw.demo.util.constants.UserRole;
import com.huyraw.demo.util.constants.UserStatus;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "name")
    @Mapping(target = "dob", source = "dob", dateFormat = "dd/MM/yyyy")
    User toUser(UserDTO userDTO);


    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);


    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "dob", source = "dob", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "fullName", source = "name")
    User updateUser(UpdateUserRequest req, @MappingTarget User user);


    default User createUser(CreateUserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setFullName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setDob(LocalDate.parse(request.getDob(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        return user;

    }
}
