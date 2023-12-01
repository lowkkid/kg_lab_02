module com.example.kg {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencv;


    opens com.example.kg2 to javafx.fxml;
    exports com.example.kg2;
}