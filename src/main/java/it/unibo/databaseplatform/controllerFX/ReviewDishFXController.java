package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ReviewDishFXController implements FXController{

    private static final int MAX_LEN = 100;
    private View view;
    private String dishCode;
    @FXML
    private ChoiceBox<Integer> ratingBox;
    @FXML
    private TextArea textBox;
    @FXML
    private Button saveReview;


    @Override
    public void setView(View view) {
        this.view = view;
        ratingBox.getItems().addAll(1,2,3,4,5);
        saveReview.setOnAction(e -> {
            var text = this.textBox.getText();
            if(text.length() > MAX_LEN) {
                text = text.substring(0, MAX_LEN);
            }
            final var rating = ratingBox.getValue();
            if(text.isEmpty() || rating == null) {
                this.textBox.setText("Recensione non valida");
            } else {
                this.view.getController().saveReview(dishCode, text, rating);
                ((Button)e.getSource()).setDisable(true);
            }
        });
    }

    public void setDishToReview(final String code) {
        this.dishCode = code;
    }

    @FXML
    public void back() {
        try {
            this.view.setScene("review-dish");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
