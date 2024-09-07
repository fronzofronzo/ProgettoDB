package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Allergen {

    private final String allergenCode;
    private final String name;

    public Allergen(String allergenCode, String name) {
        this.allergenCode = allergenCode;
        this.name = name;
    }

    public String getAllergenCode() {
        return allergenCode;
    }

    public String getName() {
        return name;
    }

    public static final class DAO {
        public static List<Allergen> getAllAllergens(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ALL_ALLERGENS);
                    final var resultSet = statement.executeQuery();
            ) {
                final List<Allergen> allergensList = new ArrayList<>();
                while(resultSet.next()) {
                    final var allergen = new Allergen(resultSet.getString(1), resultSet.getString(2));
                    allergensList.add(allergen);
                }
                return allergensList;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
