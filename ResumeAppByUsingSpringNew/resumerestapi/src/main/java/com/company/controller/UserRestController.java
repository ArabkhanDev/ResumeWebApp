package com.company.controller;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserRestController {

    @Autowired
    @Qualifier("userDao1")
    private UserRepositoryCustom userRepo;

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "surname",required = false) String surname,
            @RequestParam(name = "age",required = false) Integer age
    ){
        List<User> users =userRepo.getAll(name,surname,age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i=0;i<users.size();i++){
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
//        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUsers(@PathVariable("id") int id
    ){
        User user =userRepo.getById(id);


        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
//        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

}
