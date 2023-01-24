module ecobike.views {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires lombok;
    requires java.xml.bind;
    requires com.fasterxml.jackson.databind;
    requires java.sql;


    //opens ecobike.ecobikesystem to javafx.fxml;
    //exports ecobike.ecobikesystem;
    opens ecobike.views to javafx.fxml;
    exports ecobike.views;
}