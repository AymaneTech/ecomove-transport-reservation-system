package com.wora.common.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<Entity, ID> {

    List<Entity> findAll();

    Optional<Entity> findById(ID id);

    void create(Entity partner);

    void update(ID id, Entity partner);

    void delete(ID id);
}
