package dev.yhpark.studytest.controllers;

import dev.yhpark.studytest.entities.UserEntity;
import dev.yhpark.studytest.results.user.RegisterResult;
import dev.yhpark.studytest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "dev.yhpark.studytest.controllers.UserController")
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @RequestMapping(value = "register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView postRegister(UserEntity user) {
        RegisterResult result = this.userService.register(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/register");
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}