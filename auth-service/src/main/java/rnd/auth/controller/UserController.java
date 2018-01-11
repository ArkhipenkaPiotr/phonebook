package rnd.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;
import rnd.auth.entity.User;
import rnd.auth.repository.UserRepository;
import rnd.auth.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@EnableResourceServer
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Principal user(Principal user) {
//        String us = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return user;
    }

    @CrossOrigin
    @RequestMapping(value = "/user",params = {"login", "password"},method = RequestMethod.GET)
    @ResponseBody
    public User user(@RequestParam("login") String login, @RequestParam("password") String password){
        return userService.loadUserByUsernameAndPassword(login,password);
    }

    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getusers(){
        return userRepository.findAll();
    }
}
