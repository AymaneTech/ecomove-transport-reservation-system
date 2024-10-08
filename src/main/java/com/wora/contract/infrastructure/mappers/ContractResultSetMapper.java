package com.wora.contract.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class ContractResultSetMapper implements BaseEntityResultSetMapper<Contract> {

    public Contract map(final ResultSet resultSet) throws SQLException {
        return new Contract(
                new ContractId((UUID) resultSet.getObject("id")),
                resultSet.getString("special_price"),
                resultSet.getString("agreement_condition"),
                resultSet.getBoolean("renewable"),
                resultSet.getDate("started_at"),
                resultSet.getDate("ends_at"),
                ContractStatus.valueOf(resultSet.getString("status")),
                new PartnerId((UUID) resultSet.getObject("partner_id")),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at").toLocalDateTime(),
                resultSet.getTimestamp("deleted_at").toLocalDateTime()
        );
    }

    public void map(final Contract contract, final PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, contract.getSpecialPrice());
        stmt.setString(count++, contract.getAgreementCondition());
        stmt.setBoolean(count++, contract.getRenewable());
        stmt.setTimestamp(count++, new Timestamp(contract.getStartedAt().getTime()));
        stmt.setTimestamp(count++, new Timestamp(contract.getEndsAt().getTime()));
        stmt.setString(count++, contract.getStatus().toString());
        stmt.setObject(count++, contract.getPartnerId().value());
        stmt.setObject(count++, contract.getId().value());
    }

    private Date getDate(final String columnName, final ResultSet resultSet) throws SQLException {
        if (resultSet.getTimestamp(columnName) == null) {
            return null;
        }
        return new Date(resultSet.getTimestamp(columnName).getTime());
    }

}
