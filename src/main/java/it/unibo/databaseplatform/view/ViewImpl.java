package it.unibo.databaseplatform.view;

import it.unibo.databaseplatform.App;
import it.unibo.databaseplatform.controller.Controller;
import it.unibo.databaseplatform.controllerFX.FXController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewImpl implements View{

    private final Stage stage;
    private Controller controller;
    private FXController fxController;

    public ViewImpl(final Stage stage) throws IOException {
        this.stage = stage;
        this.setScene("start-screen");
        stage.setTitle("Piattaforma ristorante");
    }

    @Override
    public void startView() {

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showDishes() {
        var listDishes = this.controller.getDishesList();
        for(int i=0; i<listDishes.size(); i++){
            var dish = listDishes.get(i);
            System.out.println(dish.getDishName());
        }
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void setScene(String scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(scene + ".fxml"));
        final Parent root = fxmlLoader.load();
        this.fxController = ((FXController)fxmlLoader.getController());
        fxController.setView(this);
        Scene sceneToLoad = new Scene(root);
        stage.setTitle("Piattaforma ristorante");
        stage.setScene(sceneToLoad);
        stage.show();
    }

    @Override
    public FXController getFXController() {
        return this.fxController;
    }
}
