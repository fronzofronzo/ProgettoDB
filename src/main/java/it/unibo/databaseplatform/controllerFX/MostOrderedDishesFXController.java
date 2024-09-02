package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

import java.io.IOException;

public class MostOrderedDishesFXController implements FXController{

    private View view;
    @FXML
    private GridPane leaderBoardGrid;

    @Override
    public void setView(final View view) {
        this.view = view;
        final var dishLabel = new Label("Piatto");
        dishLabel.setPadding(new Insets(10));
        leaderBoardGrid.add(dishLabel,0,0);
        final var ordersLabel = new Label("Numero Ordini");
        ordersLabel.setPadding(new Insets(10));
        leaderBoardGrid.add(ordersLabel,1,0);
        final var dishesList = this.view.getController().mostOrderedDishes();
        for(int i = 0; i < dishesList.size(); i++) {
            final var nameLabel = new Label(dishesList.get(i).getX());
            nameLabel.setPadding(new Insets(10));
            leaderBoardGrid.add(nameLabel, 0, i+1);
            final var numberLabel = new Label(String.valueOf(dishesList.get(i).getY()));
            numberLabel.setPadding(new Insets(10));
            leaderBoardGrid.add(numberLabel, 1, i+1);
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
