package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.net.URL;

public class MenuViewFXController implements FXController{

    private View view;

    @FXML
    private TreeView<String> menuTreeView;

    @FXML
    private Button backButton;

    @Override
    public void setView(View view) {
        this.view = view;
        this.initialize();
    }

    private void initialize() {
        var listDishes = this.view.getController().getDishesList();
        var root = new TreeItem<>("Menu");
        var dishes = new TreeItem<>("Piatti");
        var beverages = new TreeItem<>("Bevande");
        root.getChildren().addAll(dishes,beverages);
        menuTreeView.setRoot(root);
        for(int i = 0; i < listDishes.size(); i++) {
            var dish = listDishes.get(i);
            var dishname = new TreeItem<>(dish.getNomePiatto());
            dishes.getChildren().add(dishname);
            var price = new TreeItem<>("Prezzo: " + String.valueOf(dish.getPrezzoPorzione()) + " €");
            var calories = new TreeItem<>("Calorie: " + String.valueOf(dish.getApportoCaloricoPorzione()));
            var course = new TreeItem<>("Portata: " + dish.getPortata());
            var ingredients = new TreeItem<>("Ingredienti");
            dish.getIngredienti().forEach(ing -> ingredients.getChildren().add(new TreeItem<>(ing)));
            var allergens = new TreeItem<>("Allergeni");
            dish.getAllergeni().forEach(all -> allergens.getChildren().add(new TreeItem<>(all)));
            dishname.getChildren().addAll(price,calories,course,ingredients,allergens);
        }
        var listBeverages = this.view.getController().getBeveragesList();
        for (int i = 0; i<listBeverages.size(); i++) {
            var beverage = listBeverages.get(i);
            var beverageName = new TreeItem<>(beverage.getName());
            var capacity = new TreeItem<>("Capacità = " + beverage.getCapacity());
            var price = new TreeItem<>("Prezzo = " + String.valueOf(beverage.getPrice()) + " €");
            beverageName.getChildren().addAll(capacity,price);
            beverages.getChildren().add(beverageName);
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
