package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RegisterSupplyFXController implements FXController{

    @FXML
    private ChoiceBox<String> ingredientsBox;
    @FXML
    private VBox controlsBox;
    @FXML
    private ChoiceBox<String> suppliersBox;
    @FXML
    private Label textLabel;
    private View view;
    private final Spinner<Integer> quantitySpinner = new Spinner<Integer>(0,1000,0);

    @Override
    public void setView(View view) {
        this.view = view;
        controlsBox.getChildren().add(quantitySpinner);
        final var controller = this.view.getController();
        final var ingredientsList = controller.getIngredientsCode();
        this.ingredientsBox.getItems().addAll(ingredientsList);
        final var suppliersList = controller.getVatNumbers();
        this.suppliersBox.getItems().addAll(suppliersList);
    }

    @FXML
    public void backHome() {
        try {
            this.view.setScene("admin-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void saveSupply() {
        final var supplier = this.suppliersBox.getValue() == null ? "" : this.suppliersBox.getValue();
        final var ingredient = this.ingredientsBox.getValue() == null ? "" : this.ingredientsBox.getValue();
        final var quantity = quantitySpinner.getValue();
        if(supplier.isEmpty() || ingredient.isEmpty() || quantity == 0) {
            this.textLabel.setText("Parametri non validi! ");
        } else {
            this.view.getController().registerSupply(quantity,ingredient,supplier );
            this.textLabel.setText("Ordine correttamente registrato !");
        }
    }
}
