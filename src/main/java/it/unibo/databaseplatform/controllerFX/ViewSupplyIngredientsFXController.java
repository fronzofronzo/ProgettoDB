package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.awt.*;
import java.util.List;

public class ViewSupplyIngredientsFXController implements FXController{

    private View view;
    @FXML
    private ChoiceBox<String> ingredientsCodeBox;
    @FXML
    private TreeView<String> suppliesView;
    private final TreeItem<String> root = new TreeItem<>("Rifornimenti");

    @Override
    public void setView(final View view) {
        this.view = view;
        final var controller = this.view.getController();
        final var ingredientsCodeList = controller.getIngredientsCode();
        ingredientsCodeBox.getItems().addAll(ingredientsCodeList);
        suppliesView.setRoot(root);
    }

    @FXML
    private void updateSupplyView() {
        final var ingredientCode = ingredientsCodeBox.getValue();
        if(ingredientCode != null) {
            this.root.getChildren().clear();
            final var suppliesList = this.view.getController().getSuppliesByIngredient(ingredientCode);
            for(var supply: suppliesList) {
                final var supplyItem = new TreeItem<>(supply.getSupplyCode());
                root.getChildren().add(supplyItem);
                final var dateItem = new TreeItem<>("Data: " + supply.getDate().toString());
                final var quantityItem = new TreeItem<>("Quantità: " + String.valueOf(supply.getQuantity()));
                final var adminCodeItem = new TreeItem<>("Amministratore: " + supply.getAdminCode());
                final var ingredientItem = new TreeItem<>("Ingrediente: " + supply.getIngredientCode());
                final var supplierCode = new TreeItem<>("Fornitore: " + supply.getVatNumber());
                supplyItem.getChildren().addAll(List.of(dateItem, quantityItem, adminCodeItem,
                        ingredientItem, supplierCode));
            }
        }
    }
}
