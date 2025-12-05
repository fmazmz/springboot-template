package com.example.template.service;

import com.example.template.model.EntityClass;
import com.example.template.model.EntityDto;
import com.example.template.repository.EntityRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EntityService {
    private final EntityRepository entityRepo;

    public Optional<EntityDto> findById(UUID id) {
        return entityRepo.findById(id)
                .map(entity -> EntityDto.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .createdAt(entity.getCreatedAt())
                        .build()
                );
    }

    @Transactional
    public void create(EntityDto entityDto) {
        EntityClass entityClass = EntityClass.builder()
                .name(entityDto.name())
                .description(entityDto.description())
                .createdAt(entityDto.createdAt())
                .build();

        entityRepo.save(entityClass);
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            entityRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
