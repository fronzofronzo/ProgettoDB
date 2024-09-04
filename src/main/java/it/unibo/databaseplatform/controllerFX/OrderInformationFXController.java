package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

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
            final var discountItem = new TreeItem<>(discountCode);
            final var price = new TreeItem<>(String.valueOf(order.getPrice()));
            final var time = new TreeItem<>(order.getTime().toString());
            final var date = new TreeItem<>(order.getDate().toString());
            final var dishesItem = new TreeItem<>("Piatti");
            orderCode.getChildren().addAll(discountItem, price, time, date, dishesItem);
            final var dishes = this.view.getController().getDishesInOrder(order.getOrderCode());
            for(final var d : dishes) {
                final var dishItem = new TreeItem<>(d.getX()
                        + " - " + String.valueOf(d.getY()) + " pezzi.");
                dishesItem.getChildren().add(dishItem);
            }
        }
    }

}
