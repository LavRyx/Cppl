module com.example.task12_lavr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task12_lavr to javafx.fxml;
    exports com.example.task12_lavr;
}