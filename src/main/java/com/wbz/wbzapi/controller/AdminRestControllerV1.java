package com.wbz.wbzapi.controller;

import com.wbz.wbzapi.dto.UserDTO;
import com.wbz.wbzapi.entity.User;
import com.wbz.wbzapi.mapper.UserMapper;
import com.wbz.wbzapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = userMapper.toUserDTOFromAdminRequest(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
