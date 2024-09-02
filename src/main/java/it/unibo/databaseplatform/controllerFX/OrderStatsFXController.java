package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OrderStatsFXController implements FXController{

    private View view;
    @FXML
    private VBox statsBox;

    @Override
    public void setView(View view) {
        this.view = view;
        final var averagePrice = this.view.getController().getAveragePrice();
        final var priceLabel = new Label("Prezzo medio ordine: "
                + String.valueOf(averagePrice));
        statsBox.getChildren().add(priceLabel);
        final var ordersNumberPair = this.view.getController().getOrdersByHour();
        final var lunchNumber = ordersNumberPair.getX();
        final var dinnerNumber = ordersNumberPair.getY();
        final var lunchOrdersLabel = new Label("Numero ordini a pranzo (11-15): "
                + String.valueOf(lunchNumber));
        final var dinnerOrdersLabel = new Label("Numero ordini a cena (18-23): "
                + String.valueOf(dinnerNumber));
        statsBox.getChildren().add(lunchOrdersLabel);
        statsBox.getChildren().add(dinnerOrdersLabel);
        final var dishesList = this.view.getController().mostOrderedDishes();
        final var mostOrdered = dishesList.get(0);
        final var leastOrdered = dishesList.get(dishesList.size() - 1);
        final var mostOrderedLabel = new Label("Piatto più ordinato: " + mostOrdered.getX()
                + " - Numero ordini: "
                + String.valueOf(mostOrdered.getY()));
        final var leastOrderedLabel = new Label("Piatto meno ordinato: " + leastOrdered.getX()
                + " - Numero ordini: "
                + String.valueOf(leastOrdered.getY()));
        statsBox.getChildren().add(mostOrderedLabel);
        statsBox.getChildren().add(leastOrderedLabel);
    }

    @FXML
    public void backToHomePage() {
        try {
            this.view.setScene("admin-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
