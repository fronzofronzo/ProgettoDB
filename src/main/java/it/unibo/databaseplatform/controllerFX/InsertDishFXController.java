package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.beans.EventHandler;

public class InsertDishFXController implements FXController{

    @FXML
    private TextField dishNameField;
    @FXML
    private Spinner<Float> priceSpinner;
    @FXML
    private ChoiceBox<String> servingBox;
    @FXML
    private Button controlButton;
    @FXML
    private VBox centralBox;
    private View view;
    private String dishName;
    private Float price;
    private int calories;
    private String serving;

    @Override
    public void setView(View view) {
        this.view = view;
        this.controlButton.setOnAction(this::chooseIngredients);
    }

    @FXML
    private void chooseIngredients(ActionEvent event) {
        final var button = (Button)event.getSource();
        button.setText("Scegli gli allergeni");
        button.setOnAction(this::chooseAllergens);
        centralBox.getChildren().removeAll();
    }

    private void chooseAllergens(final ActionEvent event) {

    }

}
