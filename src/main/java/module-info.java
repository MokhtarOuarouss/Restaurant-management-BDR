module bdr.bdr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens bdr.bdr to javafx.fxml;
    exports bdr.bdr;
}