package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserViewFXController implements  FXController{

    private View view;
    @FXML
    private Button showMenu;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void showMenuScene() {
        try {
            this.view.setScene("menuView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
