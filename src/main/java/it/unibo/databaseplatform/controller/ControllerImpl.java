package it.unibo.databaseplatform.controller;

import it.unibo.databaseplatform.data.Piatto;
import it.unibo.databaseplatform.model.Model;
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
    public List<Piatto> getDishesList() {
        return this.model.getDishes();
    }
}
