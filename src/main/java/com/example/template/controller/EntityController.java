package com.example.template.controller;

import com.example.template.model.EntityDto;
import com.example.template.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(path = "api/v1/entities")
@AllArgsConstructor
public class EntityController {

    private final EntityService entityService;
    public static final String JSON_CONSUME = "application/json";
    public static final String JSON_PRODUCE = "application/json";


    @GetMapping(produces = JSON_PRODUCE)
    public ResponseEntity<EntityDto> findById(@RequestParam UUID id){
        return ResponseEntity.of(entityService.findById(id));
    }

    @PostMapping(consumes = JSON_CONSUME, produces = JSON_PRODUCE)
    public ResponseEntity<EntityDto> create(@RequestBody EntityDto dto){
        entityService.create(dto);
        return ResponseEntity.status(201).build();
    }

}
