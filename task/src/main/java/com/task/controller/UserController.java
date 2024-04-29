package com.task.controller;

import com.task.dto.BirthDateRangeDto;
import com.task.dto.UserDto;
import com.task.entity.User;
import com.task.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserByFields(@RequestBody UserDto userDto, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserByFields(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping("/usersByBirthDateRange")
    public ResponseEntity<List<User>> getUsersByBirthDateRange(@RequestBody BirthDateRangeDto birthDateRangeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByBirthDateRange(birthDateRangeDto));
    }
}
