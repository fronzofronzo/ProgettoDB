package it.unibo.databaseplatform.data;

import it.unibo.databaseplatform.utilities.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Order {

    private final static int BOUND = 99999;
    private final List<Dish> dishesOrdered = new LinkedList<>();
    private final List<Beverage> beveragesOrdered = new LinkedList<>();
    private Pair<String, Float> discountCode;

    public void addDishToOrder(final Dish dish) {
        dishesOrdered.add(dish);
    }

    public void addBeverageToOrder(final Beverage beverage) {
        beveragesOrdered.add(beverage);
    }

    public List<Dish> getDishesInOrder() {
        return List.copyOf(this.dishesOrdered);
    }

    public List<Beverage> getBeveragesInOrder() {
        return List.copyOf(this.beveragesOrdered);
    }

    public void removeDishFromOrder(final Dish dish) {
        this.dishesOrdered.remove(dish);
    }

    public void removeBeverageFromOrder(final Beverage beverage) {
        this.beveragesOrdered.remove(beverage);
    }

    public void setDiscountCode(final String discountCode, final Float discountValue) {
        this.discountCode = new Pair<>(discountCode, discountValue);
    }

    public String getDiscountCode() {
        return discountCode.getX();
    }

    public Float getValue() {
        return discountCode.getY();
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
            price += order.dishesOrdered.stream().map(Dish::getPrezzoPorzione).reduce(0.0F, Float::sum);
            price += order.beveragesOrdered.stream().map(Beverage::getPrice).reduce(0.0F, Float::sum);
            if (order.getValue() != null) {
                price -= order.getValue();
            }
            final var finalPrice = price;
            final var finalCode = orderCode;
            final var discountCode = order.getDiscountCode();
            final var clientCode = client.getClientCode();
            final PreparedStatement statement;
            try {
                statement = DAOUtils.prepare(connection, Queries.INSERT_ORDER
                        , finalCode, discountCode, finalPrice, clientCode);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (discountCode != null) {
                try{
                    final var statement1 = DAOUtils.prepare(connection, Queries.USE_DISCOUNT, discountCode);
                    statement1.execute();
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
            final Set<String> alreadyPresent = new HashSet<>();
            for(var dish : order.dishesOrdered) {
                if(!alreadyPresent.contains(dish.getDishCode())) {
                    alreadyPresent.add(dish.getDishCode());
                    var count = order.dishesOrdered.stream()
                            .filter(d -> d.getDishCode().equals(dish.getDishCode()))
                            .count();
                    try {
                        final PreparedStatement statement1 = DAOUtils.prepare(connection, Queries.INSERT_DISHES_ORDER,
                                finalCode, dish.getDishCode(), count);
                        statement1.execute();
                    } catch (Exception e) {
                        throw new DAOException(e);
                    }
                }
            }
            for (var bev : order.beveragesOrdered) {
                if(!alreadyPresent.contains(bev.getBeverageCode())) {
                    alreadyPresent.add(bev.getBeverageCode());
                    var count = order.beveragesOrdered.stream()
                            .filter(d -> d.getBeverageCode().equals(bev.getBeverageCode()))
                            .count();
                    try {
                        final PreparedStatement statement1 = DAOUtils.prepare(connection, Queries.INSERT_BEVERAGES_ORDER,
                                bev.getBeverageCode(), finalCode, count);
                        statement1.execute();
                    } catch (Exception e) {
                        throw new DAOException(e);
                    }
                }
            }
            try {
                final var stt = DAOUtils.prepare(connection, Queries.ADD_POINTS_TO_CARD,
                        finalPrice, client.getCardNumber());
                stt.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static List<Pair<String, Integer>> getMostOrderedDishes(final Connection connection) {
            final List<Pair<String, Integer>> dishes = new ArrayList<>();
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_MOST_ORDERED_DISHES);
                    final var resultSet = statement.executeQuery();
                    ){
                while(resultSet.next()) {
                    final Pair<String, Integer> pair = new Pair<>(resultSet.getString(1), resultSet.getInt(2));
                    dishes.add(pair);
                }
                return List.copyOf(dishes);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static float getAveragePrice(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.AVG_PRICE);
                    final var resultSet = statement.executeQuery();
                    ){
                resultSet.next();
                return resultSet.getFloat(1);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static int getOrdersLunch(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.ORDERS_LUNCH);
                    final var resultSet = statement.executeQuery();
            ){
                resultSet.next();
                return resultSet.getInt(1);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static int getOrdersDinner(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.ORDERS_DINNER);
                    final var resultSet = statement.executeQuery();
            ){
                resultSet.next();
                return resultSet.getInt(1);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }

    private static String generateCode() {
        final Random random = new Random();
        return "O" + String.valueOf(random.nextInt(BOUND));
    }
}
