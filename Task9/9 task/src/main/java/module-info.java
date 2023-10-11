module com.example.task19forma {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task9_Lavr to javafx.fxml;
    exports com.example.task9_Lavr;
}