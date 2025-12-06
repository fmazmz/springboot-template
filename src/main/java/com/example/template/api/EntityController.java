package com.example.template.api;

import com.example.template.model.ApiResponseWrapper;
import com.example.template.model.EntityDto;
import com.example.template.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/entities")
@RequiredArgsConstructor
public class EntityController implements EntityApi {

    private final EntityService entityService;

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseWrapper<EntityDto> findById(@PathVariable UUID id) {
        return entityService.findById(id)
                .map(ApiResponseWrapper::success)
                .orElse(ApiResponseWrapper.error(404));
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseWrapper<List<EntityDto>> findAll() {
        return ApiResponseWrapper.success(entityService.findAll());
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseWrapper<EntityDto> create(@RequestBody EntityDto dto) {
        return ApiResponseWrapper.created(entityService.create(dto));
    }

    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseWrapper<EntityDto> update(@PathVariable UUID id, @RequestBody EntityDto dto) {
        return entityService.update(id, dto)
                .map(ApiResponseWrapper::success)
                .orElse(ApiResponseWrapper.error(404));
    }

    @Override
    @DeleteMapping("/{id}")
    public ApiResponseWrapper<Void> delete(@PathVariable UUID id) {
        return entityService.delete(id)
                ? ApiResponseWrapper.success(null)
                : ApiResponseWrapper.error(404);
    }
}