package it.unibo.databaseplatform.data;

import java.sql.Connection;

public class Address {

    private final String street;
    private final String civicNumber;
    private final String cap;
    private final String city;

    public Address(String street, String civicNumber, String cap, String city) {
        this.street = street;
        this.civicNumber = civicNumber;
        this.cap = cap;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCivicNumber() {
        return civicNumber;
    }

    public String getCap() {
        return cap;
    }

    public String getCity() {
        return city;
    }

    public static final class DAO {

        public static void addAddress(final Connection connection,
                                      final String addressCode, final Address address, final String clientCode) {
            try{
                final var statement = DAOUtils.prepare(connection, Queries.INSERT_ADDRESS, addressCode,
                        address.getStreet(), address.getCivicNumber(), address.getCap(), address.getCity(),
                        clientCode);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
