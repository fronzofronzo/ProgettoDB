package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Order {

    private final static int BOUND = 99999;
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

    public static final class DAO {

        public static void saveOrder(final Connection connection, final Client client, final Order order) {
            String orderCode = Order.generateCode();
            try(
                    var stat = DAOUtils.prepare(connection, Queries.GET_ORDERS_CODES);
                    var codesSet = stat.executeQuery();
            ){
                final Set<String> codes = new HashSet<>();
                while(codesSet.next()) {
                    codes.add(codesSet.getString(1));
                }
                while(codes.contains(orderCode)) {
                    orderCode = Order.generateCode();
                }
            } catch (Exception e) {
                throw new DAOException(e);
            }
            var price = 0.0F;
            price += order.dishesOrdered.stream().map(Piatto::getPrezzoPorzione).reduce(0.0F, Float::sum);
            price += order.beveragesOrdered.stream().map(Beverage::getPrice).reduce(0.0F, Float::sum);
            final var finalPrice = price;
            final var finalCode = orderCode;
            final var clientCode = client.getClientCode();
            final PreparedStatement statement;
            try {
                statement = DAOUtils.prepare(connection, Queries.INSERT_ORDER, finalCode, finalPrice, clientCode);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            final Set<String> alreadyPresent = new HashSet<>();
            for(var dish : order.dishesOrdered) {
                if(!alreadyPresent.contains(dish.getCodicePiatto())) {
                    alreadyPresent.add(dish.getCodicePiatto());
                    var count = order.dishesOrdered.stream()
                            .filter(d -> d.getCodicePiatto().equals(dish.getCodicePiatto()))
                            .count();
                    try {
                        final PreparedStatement statement1 = DAOUtils.prepare(connection, Queries.INSERT_DISHES_ORDER,
                                finalCode, dish.getCodicePiatto(), count);
                        statement1.execute();
                    } catch (Exception e) {
                        throw new DAOException(e);
                    }
                }
            }
            for(var bev : order.beveragesOrdered) {
                if(!alreadyPresent.contains(bev.getBeverageCode())) {
                    alreadyPresent.add(bev.getBeverageCode());
                    var count = order.beveragesOrdered.stream()
                            .filter(d -> d.getBeverageCode().equals(bev.getBeverageCode()))
                            .count();
                    try {
                        final PreparedStatement statement1 = DAOUtils.prepare(connection, Queries.INSERT_DISHES_ORDER,
                                bev.getBeverageCode(), finalCode, count);
                        statement1.execute();
                    } catch (Exception e) {
                        throw new DAOException(e);
                    }
                }
            }
        }
    }

    private static String generateCode() {
        final Random random = new Random();
        return "O" + String.valueOf(random.nextInt(BOUND));
    }
}
