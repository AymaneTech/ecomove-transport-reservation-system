package com.wora.partner.infrastcutre.persistence;

import com.wora.common.repositories.BaseRepositoryImpl;
import com.wora.config.DatabaseConnection;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.exceptions.PartnerCreationFailedException;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PartnerRepositoryImpl extends BaseRepositoryImpl<Partner, UUID> implements PartnerRepository {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();
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
                (id, name, commercial_name, commercial_phonenumber, commercial_email, geographical_area, special_condition, transport_type, partner_status) 
                VALUES (?, ?, ?, ?, ?, ?, ?, CAST(? AS transport_type), CAST(? AS partner_status))
                """, tableName);

        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            mapper.map(partner, stmt);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new PartnerCreationFailedException("Failed to save the partner with name " + partner.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UUID id, Partner partner) {
        final String query = String.format("""
                UPDATE %s 
                SET commercial_name = ?, 
                    commercial_phone_number = ?, 
                    commercial_email = ?, 
                    geographical_area = ?, 
                    special_condition = ?, 
                    transport_type = ?, 
                    partner_status = ?,
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                AND WHERE id IS NOT ? id
                """, tableName);
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            mapper.map(partner, stmt);
            stmt.setString(9, id.toString());
            stmt.setString(10, id.toString());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PartnerNotFoundException(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating partner", e);
        } catch (PartnerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void changeStatus(UUID id, PartnerStatus status) {
        final String query = String.format("""
                UPDATE %s 
                SET partner_status = ?
                WHERE id = ?
                """);
        try (final PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, status);
            stmt.setString(2, id.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
