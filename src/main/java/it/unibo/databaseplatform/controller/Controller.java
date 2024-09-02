package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.Address;
import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Order;
import it.unibo.databaseplatform.data.Piatto;
import it.unibo.databaseplatform.utilities.Pair;

import java.util.List;

public interface Controller {

    List<Piatto> getDishesList();

    boolean userAccess(String userCode, String password);

    List<Beverage> getBeveragesList();

    void sendOrder(final Order order);

    String registerClient(final Address address, final String name, final String surname,
                          final String phoneNumber, final String email, final String password);

    List<Pair<String, Integer>> mostOrderedDishes();

    boolean adminAccess(final String adminCode, final String password);

    float getAveragePrice();

    Pair<Integer, Integer> getOrdersByHour();

    boolean addPoints(final int cardNumber, final int points);
}
