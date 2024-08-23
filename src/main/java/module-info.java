module it.unibo.databaseplatform {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens it.unibo.databaseplatform to javafx.fxml;
    exports it.unibo.databaseplatform;
}