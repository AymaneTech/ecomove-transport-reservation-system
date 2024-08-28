package com.wora;

import ma.codex.framework.Kernel;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        Kernel.run(App.class);

        System.out.println("hello world");
    }
}
