package com.wora.contract.infrastructure.persistence;

import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.contract.infrastructure.mappers.ContractResultSetMapper;

import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class ContractRepositoryImpl extends BaseRepositoryImpl<Contract, UUID> implements ContractRepository {

    private final ContractResultSetMapper mapper;
    private final String tableName = "contracts";

    public ContractRepositoryImpl(ContractResultSetMapper mapper) {
        super("contracts", mapper);
        this.mapper = mapper;
    }

    @Override
    public void create(Contract contract) {
        final String query = String.format("""
                    INSERT INTO %s
                    (special_price, agreement_condition, renewable, started_at, ends_at, status, partner_id, id)
                    VALUES (?, ?, ?, ?, ?, CAST(? AS contract_status), ?, ?)
                """, tableName);

        executeUpdatePreparedStatement(query, stmt -> {
            mapper.map(contract, stmt);
        });
    }

    @Override
    public void update(UUID id, Contract contract) {
        final String query = String.format("""
                UPDATE %s
                SET special_price = ?, agreement_condition = ?, renewable = ?, started_at = ?, ends_at = ?, status = CAST(? AS contract_status), partner_id = ?, updated_at = CURRENT_TIMESTAMP
                WHERE id = CAST(? as uuid)
                AND deleted_at IS NULL
                """, tableName);
        executeUpdatePreparedStatement(query, stmt -> {
            mapper.map(contract, stmt);
        });
    }

    @Override
    public void changeStatus(UUID id, ContractStatus status) {
        final String query = String.format("""
                UPDATE %s 
                SET contract_status = ?
                updated_at CURRENT_TIMESTAMP
                WHERE id = ?
                """);
        executeUpdatePreparedStatement(query, stmt -> {
            stmt.setObject(1, status);
            stmt.setString(2, id.toString());
        });
    }
}
