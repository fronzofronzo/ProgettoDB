package it.unibo.databaseplatform.data;

import it.unibo.databaseplatform.utilities.Pair;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrderInformation {

    private final String orderCode;
    private final String discountCode;
    private final float price;
    private final Time time;
    private final Date date;

    public OrderInformation(String orderCode, String discountCode, float price, Time time, Date date) {
        this.orderCode = orderCode;
        this.discountCode = discountCode;
        this.price = price;
        this.time = time;
        this.date = date;
    }


    public String getOrderCode() {
        return this.orderCode;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public float getPrice() {
        return this.price;
    }

    public Time getTime() {
        return this.time;
    }

    public Date getDate() {
        return this.date;
    }

    public static final class DAO {

        public static List<OrderInformation> getOrdersFromClient(final Connection connection, final String clientCode) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ORDERS_FROM_CLIENT, clientCode);
                    final var resultSet = statement.executeQuery();
                    ){
                final List<OrderInformation> ordersList = new ArrayList<>();
                while(resultSet.next()) {
                    final var code = resultSet.getString(1);
                    final var discount = resultSet.getString(2);
                    final var price = resultSet.getFloat(3);
                    final var time = resultSet.getTime(4);
                    final var date = resultSet.getDate(5);
                    final var orderInformation = new OrderInformation(code,discount,price,time,date);
                    ordersList.add(orderInformation);
                }
                return List.copyOf(ordersList);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static List<Pair<String, Integer>> getDishesFromOrder(final Connection connection,
                                                                     final String orderCode) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_DISHES_FROM_ORDER, orderCode);
                    final var resultSet = statement.executeQuery();
                    ){
                final List<Pair<String, Integer>> dishesList = new ArrayList<>();
                while(resultSet.next()) {
                    final var name = resultSet.getString(1);
                    final var qta = resultSet.getInt(2);
                    final var informationPair = new Pair<>(name, qta);
                    dishesList.add(informationPair);
                }
                return List.copyOf(dishesList);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
