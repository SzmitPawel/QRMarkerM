module generator.qrmarkerm {
    requires javafx.controls;
    requires javafx.fxml;


    opens generator.qrmarkerm to javafx.fxml;
    exports generator.qrmarkerm;
}