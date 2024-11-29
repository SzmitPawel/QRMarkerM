module generator.qrmarkerm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.zxing;
    requires com.google.zxing.javase;


    opens generator.qrmarkerm to javafx.fxml;
    exports generator.qrmarkerm;
}