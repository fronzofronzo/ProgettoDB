package it.unibo.databaseplatform.view;

import it.unibo.databaseplatform.App;
import it.unibo.databaseplatform.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewImpl implements View{

    private final Stage stage;
    private Controller controller;

    public ViewImpl(final Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("start-screen.fxml"));
        final Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Piattaforma ristorante");
        stage.setScene(scene);
    }

    @Override
    public void startView() {

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
