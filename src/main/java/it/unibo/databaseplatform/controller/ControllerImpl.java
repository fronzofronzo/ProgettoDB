package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.Address;
import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Order;
import it.unibo.databaseplatform.data.Piatto;
import it.unibo.databaseplatform.model.Model;
import it.unibo.databaseplatform.utilities.Pair;
import it.unibo.databaseplatform.view.View;

import java.util.List;

public class ControllerImpl implements Controller{

    private final Model model;
    private final View view;

    public ControllerImpl(final Model model, final View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public List<Piatto> getDishesList() {
        return this.model.getDishes();
    }

    @Override
    public boolean userAccess(String userCode, String password) {
        return this.model.userAccess(userCode,password);
    }

    @Override
    public List<Beverage> getBeveragesList() {
        return this.model.getBeverages();
    }

    @Override
    public void sendOrder(final Order order) {
        this.model.registerOrder(order);
    }

    @Override
    public String registerClient(Address address, String name, String surname,
                                 String phoneNumber, String email, String password) {
        return this.model.registerClient(address, name,surname,phoneNumber,email,password);
    }

    @Override
    public List<Pair<String, Integer>> mostOrderedDishes() {
        return this.model.getMostOrderedDishes();
    }


}
