package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
    private final Map<CheckBox, String> ingredientsSelection = new HashMap<>();
    private final Map<CheckBox, String> allergensSelection = new HashMap<>();

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
        final ListView<Node> listView = new ListView<>();
        centralBox.getChildren().add(listView);
        VBox.setVgrow(listView, Priority.ALWAYS);
        for (var ingredient : ingredientsList) {
            final var hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            final var code = ingredient.getIngredientCode();
            final var name = ingredient.getName();
            final var checkBox = new CheckBox();
            ingredientsSelection.put(checkBox, code);
            final var label = new Label(code + " - " + name);
            label.getStyleClass().add("normal");
            hbox.getChildren().addAll(List.of(label,checkBox));
            listView.getItems().add(hbox);
        }
    }

    private void chooseAllergens(final ActionEvent event) {
        final var button = (Button)event.getSource();
        button.setText("Salva piatto");
        button.setOnAction(this::saveDish);
        centralBox.getChildren().clear();
        final var allergensList = this.view.getController().getAllAllergens();
        final ListView<Node> listView = new ListView<>();
        centralBox.getChildren().add(listView);
        VBox.setVgrow(listView, Priority.ALWAYS);
        for (var allergen : allergensList) {
            final var hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            final var code = allergen.getAllergenCode();
            final var name = allergen.getName();
            final var checkBox = new CheckBox();
            allergensSelection.put(checkBox, code);
            final var label = new Label(code + " - " + name);
            label.getStyleClass().add("normal");
            hbox.getChildren().addAll(List.of(label,checkBox));
            listView.getItems().add(hbox);
        }
    }

    private void saveDish(final ActionEvent event) {

    }

}
