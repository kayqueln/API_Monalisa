package br.com.kayCompany.Monalisa.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping
    public ResponseEntity<HttpEntity<JsonObject>> statusApp(){
        String json = "{'status' : 'health'}";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(json, header, HttpStatus.OK);

    }
}
