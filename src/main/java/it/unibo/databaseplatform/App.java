package it.unibo.databaseplatform;

import it.unibo.databaseplatform.controller.Controller;
import it.unibo.databaseplatform.controller.ControllerImpl;
import it.unibo.databaseplatform.model.Model;
import it.unibo.databaseplatform.model.ModelImpl;
import it.unibo.databaseplatform.view.View;
import it.unibo.databaseplatform.view.ViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        final View view = new ViewImpl(stage);
        final Model model = new ModelImpl();
        final Controller controller = new ControllerImpl(model, view);
        view.setController(controller);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}