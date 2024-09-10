package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReviewDishesFXController implements FXController{


    private View view;
    @FXML
    private ListView<Node> dishesList;
    private Map<Button,String> buttonDishes = new HashMap<>();

    @Override
    public void setView(View view) {
        this.view = view;
        final var dishesToReview = this.view.getController().getDishesToReview();
        for(int i = 0; i < dishesToReview.size(); i++) {
            final var dish = dishesToReview.get(i);
            final var nameLabel = new Label(dish.getY());
            final var button = new Button("Recensisci");
            final var hbox = new HBox(nameLabel, button);
            hbox.setSpacing(15);
            buttonDishes.put(button, dish.getX());
            button.setOnAction( e -> {
                final var d = buttonDishes.get((Button)e.getSource());
                try {
                    this.view.setScene("review-dish-2");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ((ReviewDishFXController)this.view.getFXController()).setDishToReview(d);
            });
            dishesList.getItems().add(hbox);
        }
    }

    @FXML
    public void backHome() {
        try {
            this.view.setScene("user-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
