package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.*;
import it.unibo.databaseplatform.utilities.Pair;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class ModelImpl implements Model{

    private final Connection connection;
    private Client actualClient;
    private Admin actualAdmin;

    public ModelImpl(Connection connection) {
        Objects.requireNonNull(connection, "Model created with null connection");
        this.connection = connection;
    }

    public List<Piatto> getDishes(){
        return Piatto.DAO.getPiatti(this.connection);
    }

    @Override
    public boolean userAccess(String code, String password) {
        actualClient = Client.DAO.getFromCode(this.connection, code ,password);
        return  actualClient != null;
    }

    @Override
    public List<Beverage> getBeverages() {
        return Beverage.DAO.getBeverages(this.connection);
    }

    @Override
    public void registerOrder(final Order order) {
        Order.DAO.saveOrder(this.connection, this.actualClient, order);
    }

    @Override
    public String registerClient(Address address, String name, String surname, String phoneNumber, String email, String password) {
        return Client.DAO.addClient(this.connection, name, surname,phoneNumber,email,password,address);
    }

    @Override
    public List<Pair<String, Integer>> getMostOrderedDishes() {
        return Order.DAO.getMostOrderedDishes(this.connection);
    }

    @Override
    public boolean adminAccess(String code, String password) {
        actualAdmin = Admin.DAO.getAdmin(this.connection, code, password);
        return actualAdmin != null;
    }

    @Override
    public float getAverageOrderPrice() {
        return Order.DAO.getAveragePrice(this.connection);
    }

    @Override
    public Pair<Integer, Integer> getOrdersNumberByHour() {
        return new Pair<>(Order.DAO.getOrdersLunch(this.connection),
                Order.DAO.getOrdersDinner(this.connection));
    }

    @Override
    public boolean addPoints(int cardNumber, int points) {
        try {
            FidelityCard.DAO.addPointsToCard(this.connection,cardNumber,points);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Gets dishes ordered by the ratings of the reviews
     *
     * @return {@link List} of {@link Pair} containing name of dish and
     * the relative average rating.
     */
    @Override
    public List<Pair<String, Float>> getMostReviewedDishes() {
        return Review.DAO.getMostReviewed(this.connection);
    }

    /**
     * Gets dishes that the client can review.
     *
     * @return {@link List} of {@link Pair} containing code and name of dishes.
     */
    @Override
    public List<Pair<String, String>> getDishesToReview() {
        return Review.DAO.getDishesToReview(this.connection, actualClient.getClientCode());
    }

    @Override
    public void saveReview(String dishCode, String text, int rating) {
        Review.DAO.saveReview(this.connection, this.actualClient.getClientCode(),
                dishCode, text, rating);
    }

    @Override
    public List<OrderInformation> getOrdersInformationByClient() {
        final var clientCode = actualClient.getClientCode();
        return OrderInformation.DAO.getOrdersFromClient(this.connection, clientCode);
    }

    @Override
    public List<Pair<String, Integer>> getDishesInOrder(final String orderCode) {
        return OrderInformation.DAO.getDishesFromOrder(this.connection, orderCode);
    }

    @Override
    public List<Pair<String, Integer>> getBeveragesFromOrder(String orderCode) {
        return OrderInformation.DAO.getBeveragesFromOrder(this.connection, orderCode);
    }

    @Override
    public FidelityCard getCardOfClient() {
        return FidelityCard.DAO.getFidelityCard(this.connection, actualClient.getClientCode());
    }
}
