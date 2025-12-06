package com.example.template.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


import java.util.Date;
import java.util.UUID;


@Builder
@Schema(name = "Entity", description = "Entity resource")
public record EntityDto(
        UUID id,
        String name,
        String description,
        Date createdAt
) {}