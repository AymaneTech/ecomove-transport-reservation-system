package com.wora.contract.infrastructure.mappers;

import com.wora.common.mappers.BaseEntityResultSetMapper;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ContractResultSetMapper implements BaseEntityResultSetMapper<Contract> {

    public Contract map(final ResultSet resultSet) {
        try {
            return new Contract(
                    new ContractId((UUID) resultSet.getObject("id")),
                    resultSet.getString("special_price"),
                    resultSet.getString("agreement_condition"),
                    resultSet.getBoolean("renewable"),
                    resultSet.getDate("started_at"),
                    resultSet.getDate("ends_at"),
                    ContractStatus.valueOf(resultSet.getString("status")),
                    new PartnerId((UUID) resultSet.getObject("partner_id"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void map(final Contract contract, final PreparedStatement stmt) {
        int count = 1;

        try {
            stmt.setObject(count++, contract.getId().value());
            stmt.setString(count++, contract.getSpecialPrice());
            stmt.setString(count++, contract.getAgreementCondition());
            stmt.setBoolean(count++, contract.getRenewable());
            stmt.setDate(count++, new java.sql.Date(contract.getStartedAt().getTime()));
            stmt.setDate(count++, new java.sql.Date(contract.getEndsAt().getTime()));
            stmt.setString(count++, contract.getStatus().toString());
            stmt.setObject(count++, contract.getPartnerId().value());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
