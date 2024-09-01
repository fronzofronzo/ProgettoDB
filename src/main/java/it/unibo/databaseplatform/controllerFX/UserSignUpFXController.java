package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.data.Address;
import it.unibo.databaseplatform.data.Client;
import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserSignUpFXController implements FXController{

    private View view;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField civicField;
    @FXML
    private TextField capField;
    @FXML
    private TextField cityField;
    @FXML
    private Label textLabel;
    @FXML
    private Button signUpButton;


    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void signUpUser() {
        if(notValidFields()) {
            textLabel.setText("Errore: bisogna compilare tutti i campi! ");
        } else {
            final var address = new Address(streetField.getText(), civicField.getText(), capField.getText(),
                    cityField.getText());
            final var code = this.view.getController().registerClient(address, nameField.getText(),surnameField.getText(),
                    (phoneField.getText()), mailField.getText(), passwordField.getText());
            textLabel.setText("Utente registrato con il codice: " + code);
            signUpButton.setDisable(true);
        }
    }

    @FXML
    public void backToMenu() {
        try {
            this.view.setScene("accesso-utente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean notValidFields() {
        return nameField.getText().isEmpty()
                || surnameField.getText().isEmpty()
                || phoneField.getText().isEmpty()
                || mailField.getText().isEmpty()
                || passwordField.getText().isEmpty()
                || streetField.getText().isEmpty()
                || civicField.getText().isEmpty()
                || capField.getText().isEmpty()
                || cityField.getText().isEmpty();

    }

}
