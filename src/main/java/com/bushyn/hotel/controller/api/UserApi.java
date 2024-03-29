package com.bushyn.hotel.controller.api;

import com.bushyn.hotel.controller.dto.UserDto;

import com.bushyn.hotel.controller.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "user management API")
@RequestMapping("/api/v1/")
public interface UserApi {
    @ApiOperation("get user by email")
    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    UserDto getUser(@PathVariable String email);

    @ApiOperation("get all users")
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    Page<UserDto> getAllUsers(Pageable pageable);

    @ApiOperation("create user")
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    UserDto createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);

    @ApiOperation("update user")
    @PatchMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto);

    @ApiOperation("delete user")
    @DeleteMapping("/user/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
