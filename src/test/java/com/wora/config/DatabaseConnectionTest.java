package com.wora.config;


import com.wora.common.utils.Env;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.MockedStaticImpl;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.charThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DatabaseConnectionTest {
    private DatabaseConnection databaseConnection;
    private Server server;
    private MockedStatic<Env> mockedEnv;

    @BeforeEach
    void setUp() throws SQLException {
        server = Server.createTcpServer("-tcp", "tcpAllowOthers", "-tcpPort", "9300").start();

        when(Env.get("DB_URL")).thenReturn("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        when(Env.get("DB_USER")).thenReturn("sa");
        when(Env.get("DB_PASSWORD")).thenReturn("");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DatabaseConnection.closeConnection();
        server.stop();
        mockedEnv.close();
    }

    @Test
    public void testGetInstance() throws SQLException {
        // arrange
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        // assert
        assertNotNull(instance1);
        assertSame(instance1, instance2);
        assertNotNull(instance1.getConnection());
        assertFalse(instance1.getConnection().isClosed());
    }

    @Test
    public void testCloseConnection() throws SQLException {
        // arrange
        Connection connection = DatabaseConnection.getInstance().getConnection();

        // act
        connection.close();

        // assert
        assertTrue(connection.isClosed());
    }

    @Test
    public void testMultipleConnection() throws SQLException {
        // arrange
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        Connection connection1 = instance1.getConnection();

        // act
        DatabaseConnection.closeConnection();
        Connection connection2 = DatabaseConnection.getInstance().getConnection();

        // assert
        assertNotSame(connection1, connection2);
        assertTrue(connection1.isClosed());
        assertFalse(connection2.isClosed());
    }

}