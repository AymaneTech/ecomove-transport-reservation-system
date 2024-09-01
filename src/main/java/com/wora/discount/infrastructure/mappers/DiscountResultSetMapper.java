package com.wora.discount.infrastructure.mappers;

import com.wora.common.contracts.BaseEntityResultSetMapper;
import com.wora.discount.domain.entities.Discount;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.enums.ReductionType;
import com.wora.discount.domain.valueObjects.DiscountId;
import com.wora.discount.domain.valueObjects.Reduction;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DiscountResultSetMapper implements BaseEntityResultSetMapper<Discount> {
    @Override
    public Discount map(ResultSet resultSet) {
        try {
            return new Discount(
                    new DiscountId((UUID) resultSet.getObject("id")),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("conditions"),
                    new Reduction(
                            resultSet.getFloat("reduction_type"),
                            (ReductionType) resultSet.getObject("reduction_type")
                    ),
                    resultSet.getDate("started_at"),
                    resultSet.getDate("ends_at"),
                    (DiscountStatus) resultSet.getObject("status"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("updated_at"),
                    resultSet.getDate("deleted_at")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void map(Discount discount, PreparedStatement stmt) {
        int count = 1;

        try {
            stmt.setString(count++, discount.getName());
            stmt.setString(count++, discount.getDescription());
            stmt.setString(count++, discount.getConditions());
            stmt.setObject(count++, discount.getReduction().value());
            stmt.setObject(count++, discount.getReduction().type());
            stmt.setDate(count++, new Date(discount.getStartedAt().getTime()));
            stmt.setDate(count++, new Date(discount.getEndsAt().getTime()));
            stmt.setObject(count++, discount.getStatus().toString());
            stmt.setObject(count++, discount.getId().value());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
