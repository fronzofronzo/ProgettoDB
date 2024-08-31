package it.unibo.databaseplatform.data;

import java.util.LinkedList;
import java.util.List;

public class Order {

    private final List<Piatto> dishesOrdered = new LinkedList<>();
    private final List<Beverage> beveragesOrdered = new LinkedList<>();

    public void addDishToOrder(final Piatto dish) {
        dishesOrdered.add(dish);
    }

    public void addBeverageToOrder(final Beverage beverage) {
        beveragesOrdered.add(beverage);
    }

    public List<Piatto> getDishesInOrder() {
        return List.copyOf(this.dishesOrdered);
    }

    public List<Beverage> getBeveragesInOrder() {
        return List.copyOf(this.beveragesOrdered);
    }

    public void removeDishFromOrder(final Piatto dish) {
        this.dishesOrdered.remove(dish);
    }

    public void removeBeverageFromOrder(final Beverage beverage) {
        this.beveragesOrdered.remove(beverage);
    }


}
