package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Order;
import it.unibo.databaseplatform.data.Piatto;

import java.util.List;

public interface Controller {

    List<Piatto> getDishesList();

    boolean userAccess(String userCode, String password);

    List<Beverage> getBeveragesList();

    void sendOrder(final Order order);
}
