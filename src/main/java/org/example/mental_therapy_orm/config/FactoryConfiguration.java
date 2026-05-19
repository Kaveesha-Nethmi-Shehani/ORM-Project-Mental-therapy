package org.example.mental_therapy_orm.config;

import org.example.mental_therapy_orm.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Therapist.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(TherapyProgram.class)
                .addAnnotatedClass(SessionSchedule.class);
        
        try {
            String user = configuration.getProperty("hibernate.connection.username");
            if (user == null) user = configuration.getProperty("connection.username");
            String pass = configuration.getProperty("hibernate.connection.password");
            if (pass == null) pass = configuration.getProperty("connection.password");
            
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pass);
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS mental_therapy");
            stmt.close();
            conn.close();
            System.out.println("Database 'mental_therapy' verified/created successfully.");
        } catch (Exception e) {
            System.err.println("Failed to auto-create database: " + e.getMessage());
        }
        
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
