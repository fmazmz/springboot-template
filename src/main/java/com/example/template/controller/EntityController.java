package com.example.template.controller;

import com.example.template.model.EntityDto;
import com.example.template.service.EntityService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path = "api/v1/entities")
@RequiredArgsConstructor
public class EntityController {

    private final EntityService entityService;
    public static final String JSON_CONSUME = "application/json";
    public static final String JSON_PRODUCE = "application/json";


    @GetMapping(value = "/{id}", produces = JSON_PRODUCE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    public ResponseEntity<EntityDto> findById(@PathVariable UUID id) {
        Optional<EntityDto> entityDto = entityService.findById(id);
        return entityDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping(consumes = JSON_CONSUME, produces = JSON_PRODUCE)
    @ApiResponse(responseCode = "201", description = "Entity created successfully")
    public ResponseEntity<EntityDto> create(@RequestBody EntityDto dto) {
        EntityDto createdEntity = entityService.create(dto);
        return ResponseEntity.status(201).body(createdEntity);
    }

}
