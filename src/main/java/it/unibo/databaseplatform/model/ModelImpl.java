package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.Beverage;
import it.unibo.databaseplatform.data.Client;
import it.unibo.databaseplatform.data.Piatto;

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
}
