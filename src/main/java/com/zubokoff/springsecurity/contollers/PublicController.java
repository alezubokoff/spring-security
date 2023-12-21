package com.zubokoff.springsecurity.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public")
public class PublicController {

    @GetMapping
    public String index() {
        return "Chegou aqui!";
    }
}
