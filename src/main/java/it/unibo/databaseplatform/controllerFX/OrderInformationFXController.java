package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.util.List;

public class OrderInformationFXController implements FXController{

    private View view;
    @FXML
    private TreeView<String> informationView;

    @Override
    public void setView(View view) {
        this.view = view;
        final var root = new TreeItem<>("Ordini");
        informationView.setRoot(root);
        final var ordersList = this.view.getController().getOrdersByClient();
        for(final var order: ordersList) {
            final var orderCode = new TreeItem<>(order.getOrderCode());
            root.getChildren().add(orderCode);
            final var discountCode = order.getDiscountCode() == null ? "" : order.getDiscountCode();
            final var discountItem = new TreeItem<>("Codice sconto: " + discountCode);
            final var price = new TreeItem<>("Prezzo totale: " + String.valueOf(order.getPrice()));
            final var time = new TreeItem<>("Orario: " + order.getTime().toString());
            final var date = new TreeItem<>("Data: " + order.getDate().toString());
            final var dishesItem = new TreeItem<>("Piatti");
            final var beveragesItem = new TreeItem<>("Bevande");
            orderCode.getChildren().addAll(List.of(discountItem, price, time, date, dishesItem, beveragesItem));
            final var dishes = this.view.getController().getDishesInOrder(order.getOrderCode());
            for(final var d : dishes) {
                final var dishItem = new TreeItem<>(d.getX()
                        + " - " + String.valueOf(d.getY()) + " pezzi.");
                dishesItem.getChildren().add(dishItem);
            }
            final var beverages = this.view.getController().getBeveragesFromOrder(order.getOrderCode());
            for(final var b : beverages) {
                final var beverageItem = new TreeItem<>(b.getX()
                        + "-"
                        + String.valueOf(b.getY())
                        + " pezzo/i");
                beveragesItem.getChildren().add(beverageItem);
            }
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
