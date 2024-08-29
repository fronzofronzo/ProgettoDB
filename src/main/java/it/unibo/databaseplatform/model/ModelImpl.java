package it.unibo.databaseplatform.model;

import java.sql.Connection;
import java.util.Objects;

public class ModelImpl implements Model{

    private final Connection connection;

    public ModelImpl(Connection connection) {
        Objects.requireNonNull(connection, "Model created with null connection");
        this.connection = connection;
    }
}
