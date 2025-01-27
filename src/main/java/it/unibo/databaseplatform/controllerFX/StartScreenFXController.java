package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class StartScreenFXController implements FXController{

    private View view;

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    @FXML
    public void setUserView() {
        try {
            this.view.setScene("accesso-utente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setAdminView() {
        try {
            this.view.setScene("admin-sign-in");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
