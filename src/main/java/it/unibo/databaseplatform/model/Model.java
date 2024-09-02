package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.Address;
import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Order;
import it.unibo.databaseplatform.data.Piatto;
import it.unibo.databaseplatform.utilities.Pair;

import java.util.List;

public interface Model {

    List<Piatto> getDishes();

    boolean userAccess(String code, String password);

    List<Beverage> getBeverages();

    void registerOrder(final Order order);

    String registerClient(Address address, String name, String surname,
                          String phoneNumber, String email, String password);

    List<Pair<String,Integer>> getMostOrderedDishes();

    boolean adminAccess(String code, String password);

    float getAverageOrderPrice();

    /**
     * Method to get the number of orders for lunch and dinner
     * @return {@link Pair} where the first parameter is the number
     * of orders of lunch and the second is the number of orders of
     * dinner.
     */
    Pair<Integer, Integer> getOrdersNumberByHour();

    boolean addPoints(final int cardNumber, final int points);
}
