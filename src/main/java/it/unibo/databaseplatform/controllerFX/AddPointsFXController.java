package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPointsFXController implements FXController{

    private View view;
    @FXML
    private TextField cardField;
    @FXML
    private TextField pointsField;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void addPoints() {

    }

}
