package com.example.template.service;

import com.example.template.model.EntityClass;
import com.example.template.model.EntityDto;
import com.example.template.repository.EntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntityService {

    private final EntityRepository entityRepo;


    public Optional<EntityDto> findById(UUID id) {
        log.debug("Finding entity by id: {}", id);
        return entityRepo.findById(id)
                .map(this::mapToDto);
    }


    public List<EntityDto> findAll() {
        log.debug("Retrieving all entities");
        return entityRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public EntityDto create(EntityDto entityDto) {
        log.debug("Creating new entity with name: {}", entityDto.name());

        EntityClass entityClass = EntityClass.builder()
                .name(entityDto.name())
                .description(entityDto.description())
                .createdAt(new Date())
                .build();

        EntityClass savedEntity = entityRepo.save(entityClass);
        log.info("Entity created successfully with id: {}", savedEntity.getId());

        return mapToDto(savedEntity);
    }


    @Transactional
    public Optional<EntityDto> update(UUID id, EntityDto entityDto) {
        log.debug("Updating entity with id: {}", id);

        return entityRepo.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(entityDto.name());
                    existingEntity.setDescription(entityDto.description());

                    EntityClass updatedEntity = entityRepo.save(existingEntity);
                    log.info("Entity updated successfully with id: {}", id);

                    return mapToDto(updatedEntity);
                });
    }


    @Transactional
    public boolean delete(UUID id) {
        log.debug("Attempting to delete entity with id: {}", id);

        if (entityRepo.existsById(id)) {
            entityRepo.deleteById(id);
            log.info("Entity deleted successfully with id: {}", id);
            return true;
        }

        log.warn("Entity not found for deletion with id: {}", id);
        return false;
    }


    private EntityDto mapToDto(EntityClass entity) {
        return EntityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }


    public boolean existsById(UUID id) {
        return entityRepo.existsById(id);
    }
}