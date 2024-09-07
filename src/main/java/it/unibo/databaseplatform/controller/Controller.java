package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.*;
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

    List<Pair<String,Float>> getMostReviewedDishes();

    List<Pair<String,String>> getDishesToReview();

    void saveReview(final String dishCode, final String text, final int rating);

    List<OrderInformation> getOrdersByClient();

    List<Pair<String,Integer>> getDishesInOrder(final String orderCode);

    List<Pair<String, Integer>> getBeveragesFromOrder(final String orderCode);

    FidelityCard getFidelityCardOfClient();

    void generateDiscount(final float value);

    void registerSupply(final int quantity, final String ingredientCode, final String vatNumber);

    List<String> getIngredientsCode();

    List<String> getVatNumbers();
}
