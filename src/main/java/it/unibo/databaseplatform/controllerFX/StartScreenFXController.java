package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenFXController implements FXController{

    @FXML
    private Button userButton;
    private View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void showDishes() {
        this.view.showDishes();
    }
}
