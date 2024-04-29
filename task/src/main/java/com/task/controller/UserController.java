package com.task.controller;

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

    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PostMapping("/saveUser")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, id));
    }

    @PatchMapping("/editUser")
    public ResponseEntity<UserDto> updateUserByFields(@RequestBody UserDto userDto, @RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserByFields(userDto, id));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }
}
