package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class Beverage {

    private final String beverageCode;
    private final String name;
    private final String capacity;
    private final float price;


    public Beverage(final String beverageCode, final String name, final String capacity, final float price) {
        this.beverageCode = beverageCode;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
    }


    public String getBeverageCode() {
        return this.beverageCode;
    }

    public String getName() {
        return this.name;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public float getPrice() {
        return this.price;
    }

    public static final class DAO {

        public static List<Beverage> getBeverages(Connection connection) {
            try(
                    var statement = DAOUtils.prepare(connection, Queries.GET_BEVERAGES);
                    var beverageSet = statement.executeQuery();
            ){
                final List<Beverage> beverages = new LinkedList<>();
                while(beverageSet.next()) {
                    var code = beverageSet.getString("b.CodiceBevanda");
                    var name = beverageSet.getString("b.Nome");
                    var capacity = beverageSet.getString("b.Capacità");
                    var price = beverageSet.getFloat("b.Prezzo");
                    var beverage = new Beverage(code,name,capacity,price);
                    beverages.add(beverage);
                }
                return beverages;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
