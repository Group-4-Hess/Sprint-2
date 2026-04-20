module com.group4.sprint2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.base;
    requires com.google.gson;
    requires javafx.graphics;

    exports com.group4.sprint2;
    exports com.group4.sprint2.Models;
    exports com.group4.sprint2.Managers;
    exports com.group4.sprint2.Controllers;

    opens com.group4.sprint2 to javafx.fxml;
    opens com.group4.sprint2.Controllers to javafx.fxml;
    opens com.group4.sprint2.Models to com.google.gson, javafx.fxml;
    opens com.group4.sprint2.Managers to com.google.gson, javafx.fxml;
}