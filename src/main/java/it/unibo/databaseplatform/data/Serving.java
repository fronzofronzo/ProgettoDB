package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Serving {

    private final String servingCode;
    private final String name;

    public Serving(String servingCode, String name) {
        this.servingCode = servingCode;
        this.name = name;
    }

    public String getServingCode() {
        return servingCode;
    }

    public String getName() {
        return name;
    }

    public static final class DAO {
        public static List<Serving> getAllServings(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ALL_SERVINGS);
                    final var resultSet = statement.executeQuery();
            ) {
                final List<Serving> servingsList = new ArrayList<>();
                while(resultSet.next()) {
                    final var serving = new Serving(resultSet.getString(1), resultSet.getString(2));
                    servingsList.add(serving);
                }
                return servingsList;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
