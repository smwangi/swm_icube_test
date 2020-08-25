package org.icube.ioutracker.controllers;

import org.icube.ioutracker.models.User;
import org.icube.ioutracker.payload.requests.UserRequest;
import org.icube.ioutracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/q4-iou-tracker/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?>getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest userRequest){

        if(userService.existsByName(userRequest.getName()))
            return new ResponseEntity<>("User with similar name exists",HttpStatus.BAD_REQUEST);

        User user = new User();
        user.setName(userRequest.getName());
        return new ResponseEntity<>(userService.save(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        var isRemoved = userService.deleteById(id);
        if(!isRemoved)
            return new ResponseEntity<>("User not found!",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.deleteById(id),HttpStatus.ACCEPTED);

    }
}
