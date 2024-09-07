package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Order;
import it.unibo.databaseplatform.data.Dish;
import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MakeOrderViewFXController implements FXController{

    private View view;
    private final List<Dish> dishes = new LinkedList<>();
    private final List<Beverage> beverages = new LinkedList<>();
    private final Order order = new Order();
    private final Map<Button, Dish> selectDishes = new HashMap<>();
    private final Map<Button, Beverage> selectBeverages = new HashMap<>();

    @FXML
    private GridPane centralPane;

    @FXML
    private Button controlButton;

    @FXML
    private HBox buttonContainer;

    @Override
    public void setView(final View view) {
        this.view = view;
        dishes.addAll(this.view.getController().getDishesList());
        beverages.addAll(this.view.getController().getBeveragesList());
        this.initialize();
    }

    private void initialize() {
        this.controlButton.setText("Riepilogo");
        this.centralPane.getChildren().removeAll(this.centralPane.getChildren());
        this.controlButton.setOnAction( e -> {
            this.showOrderDetails();
        });
        centralPane.add(new Label("Nome"), 0 , 0);
        centralPane.add(new Label("Prezzo"), 1 , 0);
        centralPane.add(new Label(""), 2 , 0);
        for (int i = 0; i < this.dishes.size(); i++ ){
            var d = this.dishes.get(i);
            centralPane.add(new Label(d.getDishName()), 0, i+1);
            centralPane.add(new Label((String.valueOf(d.getPrezzoPorzione())) + " €"), 1, i+1);
            var button = new Button("Aggiungi");
            this.selectDishes.put(button, d);
            button.setOnAction( e -> {
                this.order.addDishToOrder(
                        this.selectDishes.get((Button)e.getSource())
                );
            });
            centralPane.add(button, 2, i+1);
        }
        for (int i = 0; i < this.beverages.size() ; i++ ){
            var bev = this.beverages.get(i);
            centralPane.add(new Label(bev.getName()), 0, i +  this.dishes.size() + 1);
            centralPane.add(new Label( (String.valueOf(bev.getPrice())) + " €"), 1, i+this.dishes.size() + 1);
            var button = new Button("Aggiungi");
            this.selectBeverages.put(button, bev);
            button.setOnAction( e -> {
                this.order.addBeverageToOrder(
                        this.selectBeverages.get((Button)e.getSource())
                );
            });
            centralPane.add(button, 2, i+this.dishes.size() + 1);
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

    @FXML
    public void showOrderDetails() {
        var makeOrderButton = new Button("Invia ordine");
        makeOrderButton.getStyleClass().add("normal");
        makeOrderButton.setOnAction( e -> {
            this.sendOrder();
        });
        buttonContainer.getChildren().add(makeOrderButton);
        controlButton.setText("Aggiungi all'ordine");
        controlButton.setOnAction(event -> {
            this.initialize();
            buttonContainer.getChildren().remove(makeOrderButton);
        });
        centralPane.getChildren().removeAll(centralPane.getChildren());
        centralPane.add(new Label("Nome"), 0 , 0);
        centralPane.add(new Label("Prezzo"), 1 , 0);
        centralPane.add(new Label(""), 2 , 0);
        for(int i = 0; i < this.order.getDishesInOrder().size(); i++) {
            var d = this.order.getDishesInOrder().get(i);
            var nameLabel = new Label(d.getDishName());
            centralPane.add((nameLabel), 0, i+1);
            var priceLabel = new Label(String.valueOf(d.getPrezzoPorzione()));
            centralPane.add((priceLabel), 1, i+1);
            var button = new Button("Rimuovi");
            selectDishes.put(button, d);
            button.setOnAction( e -> {
                order.removeDishFromOrder(selectDishes.get((Button)e.getSource()));
                centralPane.getChildren().removeAll(nameLabel,priceLabel,button);
            });
            centralPane.add(button, 2, i+1 );
        }
        final var numDishes = this.order.getDishesInOrder().size();
        for(int i = 0; i < this.order.getBeveragesInOrder().size() ; i++) {
            var b = this.order.getBeveragesInOrder().get(i);
            var nameLabel = new Label(b.getName());
            centralPane.add((nameLabel), 0, i+1+numDishes);
            var priceLabel = new Label(String.valueOf(b.getPrice()));
            centralPane.add((priceLabel), 1, i+1+numDishes);
            var button = new Button("Rimuovi");
            selectBeverages.put(button, b);
            button.setOnAction( e -> {
                order.removeBeverageFromOrder(selectBeverages.get((Button)e.getSource()));
                centralPane.getChildren().removeAll(nameLabel,priceLabel,button);
            });
            centralPane.add(button, 2, i+1+numDishes );
        }
    }

    private void sendOrder() {
        this.view.getController().sendOrder(this.order);
    }

}
