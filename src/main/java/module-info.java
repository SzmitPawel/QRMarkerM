module generator.qrmarkerm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires javafx.swing;


    opens generator.qrmarkerm to javafx.fxml;
    exports generator.qrmarkerm;
    exports generator.qrmarkerm.fx.controller;
    opens generator.qrmarkerm.fx.controller to javafx.fxml;
}