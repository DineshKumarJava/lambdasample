package com.deloitte.poc.controller;

import com.deloitte.poc.model.UserData;
import com.deloitte.poc.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
public class UserController
{
    private UserService userService;

    UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping(value = "user/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserData createuserData(@RequestBody UserData userdata)
    {
        return userService.addUser(userdata);
    }

    @GetMapping(value = "user/getuser/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserData getuserData(
            @PathVariable(name = "id", required = true) String id)
    {
        return userService.getUser(id);
    }
}
