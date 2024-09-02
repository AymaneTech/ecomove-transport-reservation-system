package com.wora.common.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<Entity, ID> {

    List<Entity> findAll();

    Optional<Entity> findById(ID id);

    void create(Entity entity);

    void update(ID id, Entity entity);

    void delete(ID id);

    Boolean existsById(ID id);
}
