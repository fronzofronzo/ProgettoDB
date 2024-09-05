package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.data.FidelityCard;
import it.unibo.databaseplatform.utilities.Pair;
import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
    private View view;
    private FidelityCard card;
    private final Map<Button, Pair<Integer,Integer>> discountValues = new HashMap<>();

    @Override
    public void setView(View view) {
        this.view = view;
        this.card = this.view.getController().getFidelityCardOfClient();
        this.cardLabel.setText("Carta numero: " + card.getCardNumber() + " - Punti: " + card.getPoints());
        for( int i = 1; i <= NUM_DISCOUNTS; i++) {
            final var value = MULTIPLIER * i;
            final var pointsRequired = value * COST_MULTIPLIER;

        }
    }


}
