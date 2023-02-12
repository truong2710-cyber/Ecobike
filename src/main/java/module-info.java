module src.java.ecobike.views {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires lombok;
    requires java.xml.bind;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;

    opens ecobike.views to javafx.fxml;
    exports ecobike.views;
    exports ecobike.validators;
    exports ecobike.calculator;
    exports ecobike.controllers;
    exports ecobike.subsystems.barcode_subsystem;
}