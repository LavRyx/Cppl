module com.example.task19forma {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Task8_Lavr to javafx.fxml;
    exports com.example.Task8_Lavr;
}