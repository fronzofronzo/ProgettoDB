package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.awt.*;

public class ViewSupplyIngredientsFXController implements FXController{

    private View view;
    @FXML
    private ChoiceBox<String> ingredientsCodeBox;

    @Override
    public void setView(View view) {
        this.view = view;
        final var controller = this.view.getController();
        final var ingredientsCodeList = controller.getIngredientsCode();
        ingredientsCodeBox.getItems().addAll(ingredientsCodeList);
    }

    private void updateSupplyView() {

    }
}
