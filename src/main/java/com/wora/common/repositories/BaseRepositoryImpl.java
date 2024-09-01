package com.wora.common.repositories;

import com.wora.common.contracts.BaseEntityResultSetMapper;
import com.wora.common.contracts.BaseRepository;

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
        final String query = "SELECT * FROM " + tableName + " WHERE deleted_at is null";

        executeQueryStatement(query, resultSet -> {
            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
        });
        return entities;
    }

    @Override
    public Optional<Entity> findById(ID id) {
        final AtomicReference<Optional<Entity>> entity = new AtomicReference<>(Optional.empty());
        final String query = "SELECT * FROM " + tableName + "WHERE id = ? AND deleted_at is null";

        executeQueryPreparedStatement(query, stmt -> {
            stmt.setString(1, id.toString());
            final ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                entity.set(Optional.of(mapper.map(resultSet)));
            }
        });
        return entity.get();
    }

    @Override
    public void delete(ID id) {
        final String query = String.format("""
                UPDATE %s
                SET deleted_at = CURRENT_TIMESTAMP 
                WHERE id = ?""", tableName);

        executeQueryPreparedStatement(query, stmt -> {
            stmt.setString(1, id.toString());
        });
    }
}
