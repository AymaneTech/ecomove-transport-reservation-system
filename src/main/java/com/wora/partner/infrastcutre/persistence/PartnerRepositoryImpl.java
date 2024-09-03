package com.wora.partner.infrastcutre.persistence;

import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;

import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class PartnerRepositoryImpl extends BaseRepositoryImpl<Partner, UUID> implements PartnerRepository {

    private final PartnerResultSetMapper mapper;
    private final String tableName = "partners";

    public PartnerRepositoryImpl(PartnerResultSetMapper mapper) {
        super("partners", mapper);
        this.mapper = mapper;
    }

    @Override
    public void create(Partner partner) {
        final String query = String.format("""
                INSERT INTO %s 
                (name, commercial_name, commercial_phonenumber, commercial_email, geographical_area, special_condition, transport_type, partner_status, id) 
                VALUES (?, ?, ?, ?, ?, ?, CAST(? AS transport_type), CAST(? AS partner_status), ?)
                """, tableName);

        executeUpdatePreparedStatement(query, stmt -> mapper.map(partner, stmt));
    }

    @Override
    public void update(UUID id, Partner partner) {
        final String query = String.format("""
                UPDATE %s
                SET name = ?,
                    commercial_name = ?,
                    commercial_phonenumber = ?,
                    commercial_email = ?,
                    geographical_area = ?,
                    special_condition = ?,
                    transport_type = CAST(? AS transport_type),
                    partner_status = CAST(? AS partner_status),
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                AND deleted_at IS NULL
                """, tableName);

        executeUpdatePreparedStatement(query, stmt -> mapper.map(partner, stmt));
    }


    @Override
    public void changeStatus(UUID id, PartnerStatus status) {
        final String query = String.format("""
                UPDATE %s
                SET partner_status = ?
                updated_at CURRENT_TIMESTAMP
                WHERE id = ?
                """, tableName);

        executeUpdatePreparedStatement(query, stmt -> {
            stmt.setObject(1, status);
            stmt.setString(2, id.toString());
        });
    }
}
