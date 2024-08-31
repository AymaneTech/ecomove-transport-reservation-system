package com.wora.common.repositories;

import com.wora.common.mappers.BaseEntityResultSetMapper;
import com.wora.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.wora.common.utils.QueryExecutor.executeQueryPreparedStatement;
import static com.wora.common.utils.QueryExecutor.executeQueryStatement;

public abstract class BaseRepositoryImpl<Entity, ID> implements BaseRepository<Entity, ID> {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();
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
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                entities.add(mapper.map(resultSet));
            }
        });
        return entities;
    }

    @Override
    public Optional<Entity> findById(ID id) {
        final String query = "SELECT * FROM " + tableName + "WHERE id = ? AND deleted_at is null";
        final AtomicReference<Optional<Entity>> entity = new AtomicReference<>(Optional.empty());

        executeQueryPreparedStatement(query, stmt -> {
            try {
                stmt.setString(1, id.toString());
                final ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    entity.set(Optional.of(mapper.map(resultSet)));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
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

        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
