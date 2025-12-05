package com.example.template.model;

import lombok.Builder;

import java.util.Date;
import java.util.UUID;


@Builder
public record EntityDto(
        UUID id,
        String name,
        String description,
        Date createdAt
) {
}
