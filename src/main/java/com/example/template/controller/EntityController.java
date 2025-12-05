package com.example.template.controller;

import com.example.template.model.EntityDto;
import com.example.template.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpTimeoutException;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/entities")
@AllArgsConstructor
public class EntityController {

    private final EntityService entityService;


    @GetMapping
    public ResponseEntity<EntityDto> findById(@RequestParam UUID id){
        return ResponseEntity.of(entityService.findById(id));
    }

}
