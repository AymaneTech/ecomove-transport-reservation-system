package com.wora.common.contracts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface BaseEntityResultSetMapper<Entity> {
    Entity map(ResultSet resultSet);

    void map(Entity entity, PreparedStatement stmt);

}
