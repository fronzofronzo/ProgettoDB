package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.Piatto;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class ModelImpl implements Model{

    private final Connection connection;

    public ModelImpl(Connection connection) {
        Objects.requireNonNull(connection, "Model created with null connection");
        this.connection = connection;
    }

    public List<Piatto> getDishes(){
        return Piatto.DAO.getPiatti(this.connection);
    }

    @Override
    public boolean userAccess(String code, String password) {
        return false;
    }
}
