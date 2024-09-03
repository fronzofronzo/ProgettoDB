package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReviewsViewFXController implements FXController{

    private View view;
    @FXML
    private VBox leaderboardBox;

    @Override
    public void setView(View view) {
        this.view = view;
        final var dishesList = this.view.getController().getMostReviewedDishes();
        for(var dish : dishesList) {
            final var textLabel = new Label("Piatto: " + dish.getX()
                    + " - Voto medio: "
                    + String.valueOf(dish.getY()));
            this.leaderboardBox.getChildren().add(textLabel);
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
