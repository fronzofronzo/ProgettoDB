package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.data.FidelityCard;
import it.unibo.databaseplatform.utilities.Pair;
import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateDiscountFXController implements FXController{

    private static final int NUM_DISCOUNTS = 3;
    private static final int MULTIPLIER = 5;
    private static final int COST_MULTIPLIER = 10;

    @FXML
    private Label cardLabel;
    @FXML
    private VBox buttonsBox;
    @FXML
    private Label textLabel;
    private View view;
    private FidelityCard card;
    private final Map<Button, Pair<Float,Integer>> discountValues = new HashMap<>();

    @Override
    public void setView(View view) {
        this.view = view;
        final var controller = this.view.getController();
        this.card = controller.getFidelityCardOfClient();
        this.cardLabel.setText("Carta numero: " + card.getCardNumber() + " - Punti: " + card.getPoints());
        for( int i = 1; i <= NUM_DISCOUNTS; i++) {
            final Float value = (float)(MULTIPLIER * i);
            final Integer pointsRequired = (int) (value * COST_MULTIPLIER);
            final var informationPair = new Pair<>(value, pointsRequired);
            final var button = new Button(value + " euro - " + pointsRequired + " punti");
            discountValues.put(button, informationPair);
            button.setOnAction( e -> {
                final var b = (Button)e.getSource();
                final var points = discountValues.get(b).getY();
                final var val = discountValues.get(b).getX();
                if(card.getPoints() < points) {
                    textLabel.setText("Punti insufficienti!");
                } else {
                    controller.generateDiscount(val);
                    controller.addPoints(card.getCardNumber(), -points);
                    textLabel.setText("Buono generato correttamente!");
                }
            });
            buttonsBox.getChildren().add(button);
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
