package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public class ReviewDishFXController implements FXController{

    private View view;
    @FXML
    private GridPane dishesPane;
    private Map<Button,String> buttonDishes = new HashMap<>();

    @Override
    public void setView(View view) {
        this.view = view;
        final var dishesList = this.view.getController().getDishesToReview();
        dishesPane.add(new Label("Nome Piatto"),0,0);
        dishesPane.add(new Label(""),1,0);
        for(int i = 0; i < dishesList.size(); i++) {
            final var dish = dishesList.get(i);
            final var nameLabel = new Label(dish.getY());
            final var button = new Button("Recensisci");
            buttonDishes.put(button, dish.getX());
            /**
             * add button event handler
             */
            dishesPane.add(nameLabel, 0, i+1);
            dishesPane.add(button, 1, i+1);
        }
    }




}
