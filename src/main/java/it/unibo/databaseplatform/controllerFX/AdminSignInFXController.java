package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminSignInFXController implements FXController{

    private View view;
    @FXML
    private TextField adminCodeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label textLabel;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void trySignIn() {
        final var adminCode = adminCodeField.getText();
        final var password = passwordField.getText();
        if (this.view.getController().adminAccess(adminCode, password)) {
            try {
                this.view.setScene("admin-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.textLabel.setText("Credenziali non valide");
        }
    }
}
