module com.example.task10_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.task10_javafx to javafx.fxml;
    exports com.example.task10_javafx;
}