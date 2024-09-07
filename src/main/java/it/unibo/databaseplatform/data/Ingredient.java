package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private final String ingredientCode;
    private final String name;
    private final String description;
    private final int warehouseQuantity;

    public Ingredient(String ingredientCode, String name, String description, int warehouseQuantity) {
        this.ingredientCode = ingredientCode;
        this.name = name;
        this.description = description;
        this.warehouseQuantity = warehouseQuantity;
    }

    public String getIngredientCode() {
        return ingredientCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWarehouseQuantity() {
        return warehouseQuantity;
    }

    public static final class DAO {
        public static List<String> getIngredientsCode(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_INGREDIENTS_CODE);
                    final var resultSet = statement.executeQuery();
                    ) {
                final List<String> ingredientsCodeList = new ArrayList<>();
                while(resultSet.next()) {
                    ingredientsCodeList.add(resultSet.getString(1));
                }
                return List.copyOf(ingredientsCodeList);
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}