package com.wora.partner.infrastcutre.mappers;

import com.wora.common.contracts.BaseEntityResultSetMapper;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.enums.TransportType;
import com.wora.partner.domain.valueObjects.CommercialInfo;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PartnerResultSetMapper implements BaseEntityResultSetMapper<Partner> {

    public Partner map(final ResultSet resultSet) {
        try {
            return new Partner(
                    new PartnerId((UUID) resultSet.getObject("id")),
                    resultSet.getString("name"),
                    new CommercialInfo(
                            resultSet.getString("commercial_name"),
                            resultSet.getString("commercial_phonenumber"),
                            resultSet.getString("commercial_email")
                    ),
                    resultSet.getString("geographical_area"),
                    resultSet.getString("special_condition"),
                    TransportType.valueOf(resultSet.getString("transport_type")),
                    PartnerStatus.valueOf(resultSet.getString("partner_status")),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("updated_at"),
                    resultSet.getDate("deleted_at")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void map(final Partner partner, final PreparedStatement stmt) {
        int count = 1;

        try {
            stmt.setObject(count++, partner.getId());
            stmt.setString(count++, partner.getName());
            stmt.setString(count++, partner.getCommercialInfo().commercialName());
            stmt.setString(count++, partner.getCommercialInfo().commercialPhoneNumber());
            stmt.setString(count++, partner.getCommercialInfo().commercialEmail());
            stmt.setString(count++, partner.getGeographicArea());
            stmt.setString(count++, partner.getSpecialCondition());
            stmt.setObject(count++, partner.getTransportType().toString());
            stmt.setObject(count++, partner.getStatus().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
