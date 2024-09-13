package it.unibo.databaseplatform;

import it.unibo.databaseplatform.controller.Controller;
import it.unibo.databaseplatform.controller.ControllerImpl;
import it.unibo.databaseplatform.data.DAOUtils;
import it.unibo.databaseplatform.model.Model;
import it.unibo.databaseplatform.model.ModelImpl;
import it.unibo.databaseplatform.view.View;
import it.unibo.databaseplatform.view.ViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        final View view = new ViewImpl(stage);
        final Properties prop = new Properties();
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(input);
        }
        final String url = prop.getProperty("database.url");
        final String username = prop.getProperty("database.username");
        final String password = prop.getProperty("database.password");
        var connection = DAOUtils.localMySQLConnection(url, username, password);
        final Model model = new ModelImpl(connection);
        final Controller controller = new ControllerImpl(model, view);
        view.setController(controller);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}