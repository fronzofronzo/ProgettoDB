package it.unibo.databaseplatform.model;

import it.unibo.databaseplatform.data.Piatto;

import java.util.List;

public interface Model {

    List<Piatto> getDishes();

    boolean userAccess(String code, String password);
}
