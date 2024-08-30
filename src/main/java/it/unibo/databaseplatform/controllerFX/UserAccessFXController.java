package it.unibo.databaseplatform.controllerFX;

import it.unibo.databaseplatform.view.View;

public class UserAccessFXController implements FXController{

    private View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }


}
