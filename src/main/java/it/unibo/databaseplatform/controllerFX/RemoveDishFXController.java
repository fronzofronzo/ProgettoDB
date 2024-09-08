package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.io.IOException;

public class RemoveDishFXController implements FXController{

    @FXML
    private ListView<String> dishListView;
    @FXML
    private ChoiceBox<String> dishChoiceBox;
    private View view;

    @Override
    public void setView(View view) {
        this.view = view;
        this.updateDishesList();
    }

    @FXML
    private void backHome() {
        try {
            this.view.setScene("admin-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void removeDish() {
        if(this.dishChoiceBox.getValue() != null) {
            final var dish = dishChoiceBox.getValue();
            this.view.getController().removeDish(dish);
            this.updateDishesList();
        }
    }

    private void updateDishesList() {
        this.dishChoiceBox.getItems().clear();
        this.dishListView.getItems().clear();
        final var dishList = this.view.getController().getDishesList();
        for (var dish : dishList) {
            final String dishDescription = dish.getDishCode() + " - " + dish.getDishName();
            dishListView.getItems().add(dishDescription);
            dishChoiceBox.getItems().add(dish.getDishCode());
        }
    }


}
