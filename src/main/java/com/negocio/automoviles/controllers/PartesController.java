package com.negocio.automoviles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PartesController {

    @RequestMapping(value = "/partes", method = RequestMethod.GET)
    public String partes(Model model) {
        return "partes";
    }

}
