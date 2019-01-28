package br.edu.ifma.financeiroapi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @RequestMapping(
      value = "",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
    public String listar(){
        return "Hello";
    }
}
