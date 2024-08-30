package com.wora;

import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.FindAllPartnersUseCase;
import com.wora.partner.application.usecases.impl.FindAllPartnersUseCaseImpl;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;
import com.wora.partner.infrastcutre.persistence.PartnerRepositoryImpl;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        FindAllPartnersUseCase usecase = new FindAllPartnersUseCaseImpl(
                new PartnerRepositoryImpl(new PartnerResultSetMapper()),
                new PartnerMapper());
        System.out.println(usecase.execute());
    }
}


