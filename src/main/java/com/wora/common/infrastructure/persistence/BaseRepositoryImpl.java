package com.wora.common.infrastructure.persistence;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.wora.common.utils.QueryExecutor.executeQueryPreparedStatement;
import static com.wora.common.utils.QueryExecutor.executeQueryStatement;

public abstract class BaseRepositoryImpl<Entity, ID> implements BaseRepository<Entity, ID> {

    private final String tableName;
    private final BaseEntityResultSetMapper<Entity> mapper;

    public BaseRepositoryImpl(String tableName, BaseEntityResultSetMapper mapper) {
        this.tableName = tableName;
        this.mapper = mapper;
    }

    @Override
    public List<Entity> findAll() {
        final List<Entity> entities = new ArrayList<>();
        final String query = "SELECT * FROM " + tableName + " WHERE deleted_at IS NULL";

        executeQueryStatement(query, resultSet -> {
            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
        });
        return entities;
    }

    @Override
    public Optional<Entity> findById(final ID id) {
        final AtomicReference<Optional<Entity>> entity = new AtomicReference<>(Optional.empty());
        final String query = "SELECT * FROM " + tableName + " WHERE id = CAST (? AS uuid) AND deleted_at is null";

        executeQueryPreparedStatement(query, stmt -> {
            System.out.println("i'm here trying find by id");
            stmt.setObject(1, id.toString());
            final ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                entity.set(Optional.of(mapper.map(resultSet)));
            }
        });
        return entity.get();
    }

    @Override
    public Boolean existsById(final ID id) {
        final String query = "SELECT EXISTS (SELECT 1 FROM " + tableName + " WHERE id = CAST (? AS uuid))";
        AtomicReference<Boolean> exists = new AtomicReference<>(false);
        executeQueryPreparedStatement(query, stmt -> {
            stmt.setObject(1, id.toString());
            final ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                exists.set(resultSet.getBoolean(1));
            }
        });
        return exists.get();
    }

    @Override
    public void delete(final ID id) {
        final String query = String.format("""
                UPDATE %s
                SET deleted_at = CURRENT_TIMESTAMP
                WHERE id = CAST(? AS uuid)""", tableName);

        executeQueryPreparedStatement(query, stmt -> {
            stmt.setString(1, id.toString());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("error while deleting this");
            }
        });
    }
}