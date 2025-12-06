package com.example.template.api;

import com.example.template.model.ApiResponseWrapper;
import com.example.template.model.EntityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Entity Management", description = "CRUD operations for entities")
public interface EntityApi {

    @Operation(summary = "Get entity by ID")
    @ApiResponse(
            responseCode = "200", 
            description = "Success",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(
            responseCode = "404", 
            description = "Not found",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    ApiResponseWrapper<EntityDto> findById(
            @Parameter(description = "Entity ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable("id") UUID id);

    @Operation(summary = "Get all entities")
    @ApiResponse(
            responseCode = "200", 
            description = "Success",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    ApiResponseWrapper<List<EntityDto>> findAll();

    @Operation(summary = "Create new entity")
    @ApiResponse(
            responseCode = "201", 
            description = "Created",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(
            responseCode = "400", 
            description = "Invalid input",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    ApiResponseWrapper<EntityDto> create(
            @Valid @RequestBody EntityDto dto);

    @Operation(summary = "Update entity")
    @ApiResponse(
            responseCode = "200", 
            description = "Updated",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(
            responseCode = "404", 
            description = "Not found",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    ApiResponseWrapper<EntityDto> update(
            @Parameter(description = "Entity ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable("id") UUID id,
            @Valid @RequestBody EntityDto dto);

    @Operation(summary = "Delete entity")
    @ApiResponse(
            responseCode = "200", 
            description = "Deleted",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @ApiResponse(
            responseCode = "404", 
            description = "Not found",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    ApiResponseWrapper<Void> delete(
            @Parameter(description = "Entity ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable("id") UUID id);
}