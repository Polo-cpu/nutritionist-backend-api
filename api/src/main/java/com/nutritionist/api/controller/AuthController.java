package com.nutritionist.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@EnableSpringHttpSession

public class AuthController {


}
