package com.wora.discount.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.contract.domain.valueObjects.ContractId;
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
    public Discount map(ResultSet resultSet) throws SQLException {
        return new Discount(
                new DiscountId((UUID) resultSet.getObject("id")),
                new ContractId((UUID) resultSet.getObject("contract_id")),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("conditions"),
                new Reduction(
                        resultSet.getFloat("reduction_value"),
                        ReductionType.valueOf(resultSet.getString("reduction_type"))
                ),
                resultSet.getDate("started_at"),
                resultSet.getDate("ends_at"),
                DiscountStatus.valueOf(resultSet.getString("status")),
                resultSet.getDate("created_at"),
                resultSet.getDate("updated_at"),
                resultSet.getDate("deleted_at")
        );
    }

    @Override
    public void map(Discount discount, PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, discount.getName());
        stmt.setString(count++, discount.getDescription());
        stmt.setString(count++, discount.getConditions());
        stmt.setObject(count++, discount.getReduction().value());
        stmt.setObject(count++, discount.getReduction().type().toString());
        stmt.setDate(count++, new Date(discount.getStartedAt().getTime()));
        stmt.setDate(count++, new Date(discount.getEndsAt().getTime()));
        stmt.setObject(count++, discount.getStatus().toString());
        stmt.setObject(count++, discount.getContractId().value());
        stmt.setObject(count++, discount.getId().value());
    }
}
