package it.unibo.databaseplatform.view;

import it.unibo.databaseplatform.controller.Controller;

import java.io.IOException;

public interface View {

    void startView();

    void setController(final Controller controller);

    void showDishes();

    Controller getController();

    void setScene(String scene) throws IOException;
}
