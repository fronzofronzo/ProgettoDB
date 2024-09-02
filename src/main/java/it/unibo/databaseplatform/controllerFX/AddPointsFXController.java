package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddPointsFXController implements FXController{

    private View view;
    @FXML
    private TextField cardField;
    @FXML
    private TextField pointsField;
    @FXML
    private Label textLabel;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void addPoints() {
        if(this.cardField.getText().isEmpty() || this.cardField.getText().isEmpty()) {
            this.textLabel.setText("Campi vuoti: Operazione non valida !");
        }
        final var code = Integer.parseInt(this.cardField.getText());
        final var points = Integer.parseInt(this.pointsField.getText());
        if(this.view.getController().addPoints(code,points)) {
            this.textLabel.setText("Aggiunti " + points + " punti alla carta " + code);
        } else {
            this.textLabel.setText("Errore! Controllare i campi inseriti ");
        }
    }

    @FXML
    public void backHome() {
        try {
            this.view.setScene("admin-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
