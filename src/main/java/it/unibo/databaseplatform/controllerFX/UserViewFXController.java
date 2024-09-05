package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserViewFXController implements  FXController{

    private View view;
    @FXML
    private Button showMenu;
    @FXML
    private Button orderButton;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @FXML
    public void showMenuScene() {
        try {
            this.view.setScene("menuView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showOrderMaking() {
        try {
            this.view.setScene("make-order-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showMostOrderedDishes() {
        try {
            this.view.setScene("most-ordered-dishes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showMostReviewedDishes() {
        try {
            this.view.setScene("reviews-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void reviewDish() {
        try {
            this.view.setScene("review-dish");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showOrdersInformation() {
        try {
            this.view.setScene("orders-information");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void generateDiscountView() {
        try {
            this.view.setScene("generate-discount");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
