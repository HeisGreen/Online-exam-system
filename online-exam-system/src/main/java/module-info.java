module com.example.onlineexamsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.onlineexamsystem to javafx.fxml;
    exports com.example.onlineexamsystem;
}