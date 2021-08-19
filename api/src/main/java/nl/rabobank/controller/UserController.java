package nl.rabobank.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
}
