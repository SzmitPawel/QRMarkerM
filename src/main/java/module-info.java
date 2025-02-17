module generator.qrmarkerm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires javafx.swing;
    requires qr.code.with.logo;


    opens generator.qrmarkerm to javafx.fxml;
    exports generator.qrmarkerm;
    exports generator.qrmarkerm.fx.controller.controller;
    opens generator.qrmarkerm.fx.controller.controller to javafx.fxml;
    exports generator.qrmarkerm.fx.controller.button;
    opens generator.qrmarkerm.fx.controller.button to javafx.fxml;
}