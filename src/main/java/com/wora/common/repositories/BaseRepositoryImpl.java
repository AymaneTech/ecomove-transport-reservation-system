package com.wora.common.repositories;

import com.wora.common.mappers.BaseEntityResultSetMapper;
import com.wora.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        try (final Statement stmt = connection.createStatement()) {
            final ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE deleted_at is null");

            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Entity> findById(ID id) {
        try (final PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + tableName + "WHERE id = ? AND deleted_at is null")) {
            stmt.setString(1, id.toString());
            final ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(mapper.map(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
