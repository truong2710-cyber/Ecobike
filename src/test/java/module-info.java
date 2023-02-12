module test.java.ecobike.validators{
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
    requires JUnitParams;
    requires src.java.ecobike.views;
    //requires ecobike.views;

    exports ecobike.validators_test;
    exports ecobike.calculator_test;
    exports ecobike.controllers_test;
    exports ecobike.subsystems_test.barcode_subsystem_test;
}