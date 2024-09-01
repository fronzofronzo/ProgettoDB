package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.Address;
import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Order;
import it.unibo.databaseplatform.data.Piatto;

import java.util.List;

public interface Controller {

    List<Piatto> getDishesList();

    boolean userAccess(String userCode, String password);

    List<Beverage> getBeveragesList();

    void sendOrder(final Order order);

    String registerClient(final Address address, final String name, final String surname,
                          final String phoneNumber, final String email, final String password);
}
