module com.example.task11_lavr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task11_lavr to javafx.fxml;
    exports com.example.task11_lavr;
}