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

    private void updateDishesList() {
        final var dishList = this.view.getController().getDishesList();
        for (var dish : dishList) {
            final String dishDescription = dish.getDishCode() + " - " + dish.getDishName();
            dishListView.getItems().add(dishDescription);
            dishChoiceBox.getItems().add(dish.getDishCode());
        }
    }


}
