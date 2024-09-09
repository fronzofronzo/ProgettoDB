package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

import java.io.IOException;

public class MostOrderedDishesFXController implements FXController{

    private View view;
    @FXML
    private ListView<String> dishesList;

    @Override
    public void setView(final View view) {
        this.view = view;
        final var dishes = this.view.getController().mostOrderedDishes();
        for(int i = 0; i < dishes.size(); i++) {
            final var name = dishes.get(i).getX();
            final var numOrders = String.valueOf(dishes.get(i).getY());
            dishesList.getItems().add(
                    String.valueOf(i+1) + ") Piatto: "
                    + name + " - Numero ordini: "
                    + numOrders);
        }
    }

    @FXML
    public void backToMenu() {
        try {
            this.view.setScene("user-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
