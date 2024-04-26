package com.task.service;

import com.task.dto.UserDto;
import com.task.entity.User;
import com.task.exception.exceptions.BadRequestException;
import com.task.exception.exceptions.IdNotFoundException;
import com.task.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final Integer yearToPass;

    @Autowired
    public UserService(UserRepo userRepo, ModelMapper modelMapper, @Value("${yearToPass}") Integer yearToPass) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.yearToPass = yearToPass;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("The user does not exist by this id: " + id));
    }

    public UserDto saveUser(UserDto userDto) {
        checkValidBirthDate(userDto.getBirthDate());
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    private void checkValidBirthDate(LocalDate birthDate) {
        Period period = Period.between(birthDate, LocalDate.now());
        int years = period.getYears();
        if (years < yearToPass) {
            throw new BadRequestException("User must be over 18");
        }
    }

    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("The user does not exist by this id: " + id));
        checkValidBirthDate(userDto.getBirthDate());
        modelMapper.map(userDto, user);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserDto updateUserByFields(UserDto userDto, Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("The user does not exist by this id: " + id));
        checkValidBirthDate(userDto.getBirthDate());
        editUserFields(user, userDto);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    private void editUserFields(User user, UserDto userDto) {
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getBirthDate() != null) {
            user.setBirthDate(userDto.getBirthDate());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }
        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
    }

    public String deleteUser(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("The user does not exist by this id: " + id));
        userRepo.delete(user);
        return "User was deleted by this id: " + id;
    }
}
