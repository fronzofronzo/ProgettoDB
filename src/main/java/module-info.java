module it.unibo.databaseplatform {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens it.unibo.databaseplatform to javafx.fxml;
    exports it.unibo.databaseplatform;
    exports it.unibo.databaseplatform.controllerFX to javafx.fxml;
}