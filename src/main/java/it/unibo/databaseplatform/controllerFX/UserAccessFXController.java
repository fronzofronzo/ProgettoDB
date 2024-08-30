package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserAccessFXController implements FXController{

    private View view;
    @FXML
    private TextField userCode;
    @FXML
    private TextField userPassword;
    @FXML
    private Label userLabel;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void tryUserAccess() {
        var code = userCode.getText();
        var password = userPassword.getText();
        if (this.view.getController().userAccess(code,password)) {
            try {
                this.view.setScene("user-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.userLabel.setText("Utente non trovato");
        }
    }

}
