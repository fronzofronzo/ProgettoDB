package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.model.Model;
import it.unibo.databaseplatform.view.View;

public class ControllerImpl implements Controller{

    private final Model model;
    private final View view;

    public ControllerImpl(final Model model, final View view){
        this.model = model;
        this.view = view;
    }
}
