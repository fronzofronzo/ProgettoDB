package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.*;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class ModelImpl implements Model{

    private final Connection connection;
    private Client actualClient;

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
}
