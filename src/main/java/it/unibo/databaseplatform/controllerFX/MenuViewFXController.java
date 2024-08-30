package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;

public class MenuViewFXController implements FXController{

    private View view;

    @FXML
    private TreeView<String> menuTreeView;

    @Override
    public void setView(View view) {
        this.view = view;
        this.initialize();
    }

    private void initialize() {
        var listDishes = this.view.getController().getDishesList();
        var root = new TreeItem<>("Menu");
        menuTreeView.setRoot(root);
        for(int i = 0; i < listDishes.size(); i++) {
            var dish = listDishes.get(i);
            var dishname = new TreeItem<>(dish.getNomePiatto());
            root.getChildren().add(dishname);
            var price = new TreeItem<>("Prezzo = " + String.valueOf(dish.getPrezzoPorzione()));
            var calories = new TreeItem<>("Calorie = " + String.valueOf(dish.getApportoCaloricoPorzione()));
            var course = new TreeItem<>(dish.getPortata());
            var ingredients = new TreeItem<>("Ingredienti");
            dish.getIngredienti().forEach(ing -> ingredients.getChildren().add(new TreeItem<>(ing)));
            var allergens = new TreeItem<>("Allergeni");
            dish.getAllergeni().forEach(all -> allergens.getChildren().add(new TreeItem<>(all)));
            dishname.getChildren().addAll(price,calories,course,ingredients,allergens);
        }
    }

}
