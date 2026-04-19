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

    opens com.group4.sprint2 to javafx.fxml;
    opens com.group4.sprint2.Controllers to javafx.fxml;

    exports com.group4.sprint2;



}