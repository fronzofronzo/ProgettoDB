package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private final String vatNumber;
    private final String companyName;
    private final String street;
    private final String civicNumber;
    private final String cap;
    private final String city;
    private final String email;
    private final String phoneNumber;

    public Supplier(String vatNumber, String companyName, String street, String civicNumber, String cap, String city, String email, String phoneNumber) {
        this.vatNumber = vatNumber;
        this.companyName = companyName;
        this.street = street;
        this.civicNumber = civicNumber;
        this.cap = cap;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getCompanyName() {
        return companyName;
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

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static final class DAO {
        public static List<String> getVatNumbers(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_VAT_NUMBERS);
                    final var resultSet = statement.executeQuery();
            ) {
                final List<String> suppliersList = new ArrayList<>();
                while(resultSet.next()) {
                    suppliersList.add(resultSet.getString(1));
                }
                return List.copyOf(suppliersList);
            }catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
