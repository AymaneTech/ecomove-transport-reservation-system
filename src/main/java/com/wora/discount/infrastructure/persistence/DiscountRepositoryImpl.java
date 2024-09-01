package com.wora.discount.infrastructure.persistence;

import com.wora.common.repositories.BaseRepositoryImpl;
import com.wora.discount.domain.entities.Discount;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.repositories.DiscountRepository;
import com.wora.discount.infrastructure.mappers.DiscountResultSetMapper;

import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeQueryPreparedStatement;
import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class DiscountRepositoryImpl extends BaseRepositoryImpl<Discount, UUID> implements DiscountRepository {

    private final DiscountResultSetMapper mapper;
    private final String tableName = "discounts";

    public DiscountRepositoryImpl(DiscountResultSetMapper mapper) {
        super("discounts", mapper);
        this.mapper = mapper;
    }

    @Override
    public void create(Discount discount) {
        final String query = String.format("""
                INSERT INTO %s
                (name, description, conditions, reduction_value, reduction_type, started_at, ends_at, status, contract_id,  id)
                VALUES (?, ?, ?, ?, CAST(? AS reduction_type), ?, ?, CAST(? AS discount_status), ?, ?)""", tableName);

        executeQueryPreparedStatement(query, stmt -> mapper.map(discount, stmt));
    }

    @Override
    public void update(UUID uuid, Discount discount) {
        final String query = String.format("""
                UPDATE %s
                SET name = ?, description = ?, conditions = ?, reduction_value = ?, reduction_type = CAST(? AS reduction_type),
                started_at = ?, ends_at = ?, status = CAST(? AS discount_status), contract_id = ?
                WHERE id = ?""", tableName);

        executeQueryPreparedStatement(query, stmt -> mapper.map(discount, stmt));
    }

    @Override
    public void changeStatus(UUID id, DiscountStatus status) {
        final String query = String.format("""
                UPDATE %s
                SET status = CAST(? AS discount_status)
                WHERE id = ?""", tableName);

        executeUpdatePreparedStatement(query, stmt -> {
            stmt.setString(1, status.name());
            stmt.setString(2, id.toString());
        });
    }
}
