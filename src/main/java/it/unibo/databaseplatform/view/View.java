package it.unibo.databaseplatform.view;

import it.unibo.databaseplatform.controller.Controller;

public interface View {

    void startView();

    void setController(final Controller controller);
}
