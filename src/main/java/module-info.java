module com.example.algoritmer1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.algoritmer1 to javafx.fxml;
    exports com.example.algoritmer1;
}