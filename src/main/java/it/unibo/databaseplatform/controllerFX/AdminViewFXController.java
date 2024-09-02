package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminViewFXController implements FXController{

    private View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void showOrdersStats() {
        try {
            this.view.setScene("orders-stats");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void addPointsToCard() {
        try {
            this.view.setScene("add-points");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}