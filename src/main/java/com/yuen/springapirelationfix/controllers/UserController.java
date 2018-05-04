package com.yuen.springapirelationfix.controllers;

import com.yuen.springapirelationfix.entities.User;
import com.yuen.springapirelationfix.exceptions.InvalidRequestBodyException;
import com.yuen.springapirelationfix.pojo.ApiResponse;
import com.yuen.springapirelationfix.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.function.Function;
import java.util.stream.Collectors;

@Api(description = "User Service")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @ApiOperation( "find all users" )
    @GetMapping
    public ResponseEntity findAllUsers(){
        return ResponseEntity.status(200).body(userService.findAllUsers());
    }

    @ApiOperation( "add users" )
    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid  User user, Errors errors){
        if (errors.hasErrors())
            throw new InvalidRequestBodyException( getValidationErrors.apply(errors) );
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(userService.addUser(user));
    }

    @ApiOperation( "find user by id" )
    @GetMapping("/{id}")
    public ResponseEntity findUser(@PathVariable Long id){
        return ResponseEntity.status(200).body(userService.findUser(id));
    }

    @ApiOperation( "update user by id")
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody @Valid User users, Errors errors){
        if (errors.hasErrors())
            throw new InvalidRequestBodyException( getValidationErrors.apply(errors) );
        return ResponseEntity.status(200).body(userService.updateUser(id, users));
    }

    @ApiOperation( "delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(ApiResponse.builder().code(200)
                    .status("OK").message("data at id "+id+" successfully deleted").build());
        return ResponseEntity.status(400).body(ApiResponse.builder().code(400).
                status("BAD REQUEST").message("something went wrong").build());
    }

    @ApiOperation( "search users by name query string")
    @GetMapping(value = "/name")
    public ResponseEntity findUserByFirstName(@RequestParam(value = "firstName", required = false) String firstName,
                                              @RequestParam(value = "lastName", required = false) String lastName,
                                              @RequestParam(value = "middleName", required = false) String middleName,
                                              @RequestParam(value = "suffix", required = false) String suffix){
        return ResponseEntity.status(200).body(userService.findNameByQuery(firstName, middleName, lastName, suffix));
    }

    private Function<Errors,String> getValidationErrors = err ->
            err.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
}
