package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.*;
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
    public List<Dish> getDishesList() {
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

    @Override
    public boolean adminAccess(String adminCode, String password) {
        return this.model.adminAccess(adminCode, password);
    }

    @Override
    public float getAveragePrice() {
        return this.model.getAverageOrderPrice();
    }

    @Override
    public Pair<Integer, Integer> getOrdersByHour() {
        return this.model.getOrdersNumberByHour();
    }

    @Override
    public boolean addPoints(int cardNumber, int points) {
        return this.model.addPoints(cardNumber, points);
    }

    @Override
    public List<Pair<String, Float>> getMostReviewedDishes() {
        return this.model.getMostReviewedDishes();
    }

    @Override
    public List<Pair<String, String>> getDishesToReview() {
        return this.model.getDishesToReview();
    }

    @Override
    public void saveReview(String dishCode, String text, int rating) {
        this.model.saveReview(dishCode, text, rating);
    }

    @Override
    public List<OrderInformation> getOrdersByClient() {
        return this.model.getOrdersInformationByClient();
    }

    @Override
    public List<Pair<String, Integer>> getDishesInOrder(String orderCode) {
        return this.model.getDishesInOrder(orderCode);
    }

    @Override
    public List<Pair<String, Integer>> getBeveragesFromOrder(final String orderCode) {
        return this.model.getBeveragesFromOrder(orderCode);
    }

    @Override
    public FidelityCard getFidelityCardOfClient() {
        return this.model.getCardOfClient();
    }

    @Override
    public void generateDiscount(float value) {
        this.model.generateDiscount(value);
    }

    @Override
    public void registerSupply(int quantity, String ingredientCode, String vatNumber) {
        this.model.registerSupply(quantity, ingredientCode, vatNumber);
    }

    @Override
    public List<String> getIngredientsCode() {
        return this.model.getIngredientsCode();
    }

    @Override
    public List<String> getVatNumbers() {
        return this.model.getVatNumbers();
    }

    @Override
    public List<Supply> getSuppliesByIngredient(final String ingredientCode) {
        return this.model.getSupplyByIngredient(ingredientCode);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return this.model.getAllIngredients();
    }

    @Override
    public List<Allergen> getAllAllergens() {
        return this.model.getAllAllergens();
    }

    @Override
    public void registerDish(final Dish dish) {
        this.model.insertDish(dish);
    }

    @Override
    public List<Serving> getAllServings() {
        return this.model.getAllServings();
    }

    @Override
    public void removeDish(final String dishCode) {
        this.model.removeDish(dishCode);
    }

    @Override
    public List<Pair<String, Float>> getDiscountsOfClient() {
        return this.model.getAllDiscountsOfClient();
    }


}
