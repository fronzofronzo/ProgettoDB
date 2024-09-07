package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.EventHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Map<ToggleButton, String> ingredientsSelection = new HashMap<>();

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
        centralBox.getChildren().clear();
        final var ingredientsList = this.view.getController().getAllIngredients();
        for (var ingredient : ingredientsList) {
            final var toggleButton = new ToggleButton();
            toggleButton.getStyleClass().add("normal");
            ingredientsSelection.put(toggleButton, ingredient.getIngredientCode());
            final var ingredientLabel = new Label(ingredient.getIngredientCode() + " - " + ingredient.getName());
            ingredientLabel.getStyleClass().add("normal");
            final var hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(List.of(ingredientLabel, toggleButton));
            centralBox.getChildren().add(hbox);
        }
    }

    private void chooseAllergens(final ActionEvent event) {

    }

}
