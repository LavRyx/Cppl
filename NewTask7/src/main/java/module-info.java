module com.example.newtask7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.newtask7 to javafx.fxml;
    exports com.example.newtask7;
}