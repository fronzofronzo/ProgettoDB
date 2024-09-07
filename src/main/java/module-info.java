module it.unibo.databaseplatform {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens it.unibo.databaseplatform to javafx.fxml;
    exports it.unibo.databaseplatform;
    exports it.unibo.databaseplatform.controllerFX to javafx.fxml;

    // Altri requires...

    // Apre il pacchetto al modulo javafx.fxml
    opens it.unibo.databaseplatform.controllerFX to javafx.fxml;


}