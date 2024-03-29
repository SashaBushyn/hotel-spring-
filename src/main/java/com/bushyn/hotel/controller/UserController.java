package com.bushyn.hotel.controller;

import com.bushyn.hotel.controller.api.UserApi;
import com.bushyn.hotel.controller.dto.UserDto;
import com.bushyn.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public UserDto getUser(String email) {
        log.info("request to get user by email {}", email);
        return userService.getUser(email);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable page) {
        log.info("request to get all users");
        return userService.listUsers(page);
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("request to create user");
        return userService.createUser(userDto);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("request to update user by email {}", email);
        return userService.updateUser(email, userDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        log.info("request to delete user by id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
