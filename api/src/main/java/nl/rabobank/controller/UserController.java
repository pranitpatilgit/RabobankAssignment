package nl.rabobank.controller;

import io.swagger.v3.oas.annotations.Operation;
import nl.rabobank.dto.User;
import nl.rabobank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gets details of the user")
    public User getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }
}
