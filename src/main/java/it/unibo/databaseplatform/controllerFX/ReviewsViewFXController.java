package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReviewsViewFXController implements FXController{

    private View view;
    @FXML
    private ListView<Label> leaderboardView;

    @Override
    public void setView(final View view) {
        this.view = view;
        final var dishesList = this.view.getController().getMostReviewedDishes();
        int i = 1;
        for(var dish : dishesList) {
            final var textLabel = new Label(String.valueOf(i)
                    + ") Piatto: " + dish.getX()
                    + " - Voto medio: "
                    + String.valueOf(dish.getY()));
            textLabel.setPadding(new Insets(10));
            this.leaderboardView.getItems().add(textLabel);
            i++;
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
