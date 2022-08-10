module com.example.pt08_2072008 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires java.sql;
    requires java.naming;
    requires mysql.connector.java;
    requires java.persistence;
    requires org.hibernate.orm.core;

    exports com.example.pt08_2072008.dao;
    opens com.example.pt08_2072008.dao to javafx.fxml;
    exports com.example.pt08_2072008.model;
    opens com.example.pt08_2072008.model;
    exports com.example.pt08_2072008.util;
    opens com.example.pt08_2072008.util to javafx.fxml;
    exports com.example.pt08_2072008.controller;
    opens com.example.pt08_2072008.controller to javafx.fxml;
    exports com.example.pt08_2072008;
    opens com.example.pt08_2072008 to javafx.fxml;
}