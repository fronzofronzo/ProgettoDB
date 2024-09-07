package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.*;
import it.unibo.databaseplatform.utilities.Pair;

import java.util.List;

public interface Model {

    List<Dish> getDishes();

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

    /**
     * Gets dishes ordered by the ratings of the reviews
     * @return {@link List} of {@link Pair} containing name of dish and
     * the relative average rating.
     */
    List<Pair<String,Float>> getMostReviewedDishes();

    /**
     * Gets dishes that the client can review.
     * @return {@link List} of {@link Pair} containing code and name of dishes.
     */
    List<Pair<String,String>> getDishesToReview();

    void saveReview(final String dishCode, final String text, final int rating);

    List<OrderInformation> getOrdersInformationByClient();

    List<Pair<String, Integer>> getDishesInOrder(final String orderCode);

    List<Pair<String, Integer>> getBeveragesFromOrder(final String orderCode);

    FidelityCard getCardOfClient();

    void generateDiscount(final float value);

    void registerSupply(final int quantity, final String ingredientCode, final String vatNumber);

    List<String> getIngredientsCode();

    /**
     * Gets list containing Vat Numbers of all suppliers.
     * @return {@link List} containing VAT numbers;
     */
    List<String> getVatNumbers();

    /**
     * Get list of supply for a certain ingredient
     * @param ingredientCode - ingredient object of the research
     * @return {@link List} containing {@link Supply} set with the database parameters.
     */
    List<Supply> getSupplyByIngredient(final String ingredientCode);

    List<Ingredient> getAllIngredients();

    List<Allergen> getAllAllergens();

    void insertDish(final Dish dish);

    List<Serving> getAllServings();
}
