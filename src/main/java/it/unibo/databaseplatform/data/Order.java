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

    public List<String> getDishesInOrder() {
        return this.dishesOrdered.stream().map(Piatto::getNomePiatto).toList();
    }


}
