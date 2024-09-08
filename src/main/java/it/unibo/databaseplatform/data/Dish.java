package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Dish {

    private static final int NUM_BOUND = 999;

    private final String dishCode;
    private final String dishName;
    private final float prezzoPorzione;
    private final int apportoCaloricoPorzione;
    private final String portata;
    private final List<String> allergeni;
    private final List<String> ingredienti;

    public Dish(String codicePiatto, String nomePiatto, float prezzoPorzione, int apportoCaloricoPorzione,
                String portata) {
        this.dishCode = codicePiatto;
        this.dishName = nomePiatto;
        this.prezzoPorzione = prezzoPorzione;
        this.apportoCaloricoPorzione = apportoCaloricoPorzione;
        this.portata = portata;
        this.allergeni = new LinkedList<>();
        this.ingredienti = new LinkedList<>();
    }

    public String getDishCode() {
        return this.dishCode;
    }

    public String getDishName() {
        return this.dishName;
    }

    public float getPrezzoPorzione() {
        return this.prezzoPorzione;
    }

    public int getApportoCaloricoPorzione() {
        return this.apportoCaloricoPorzione;
    }

    public String getPortata() {
        return this.portata;
    }

    public List<String> getAllergeni() {
        return allergeni;
    }

    public List<String> getIngredienti() {
        return ingredienti;
    }

    public void addIngredient(String ing) {
        this.ingredienti.add(ing);
    }

    public void addAllergen(String all) {
        this.allergeni.add(all);
    }

    public static final class DAO {

        public static List<Dish> getPiatti(Connection connection) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.ALL_DISHES);
                    var resultSet = statement.executeQuery();
            ) {
                final List<Dish> listOfDishes = new ArrayList<>();
                while (resultSet.next()) {
                    var codPiatto = resultSet.getString("p.CodicePiatto");
                    var nomePiatto = resultSet.getString("p.NomePiatto");
                    var przPorzione = resultSet.getFloat("p.PrezzoPorzione");
                    var calPorzione = resultSet.getInt("p.ApportoCaloricoPorzione");
                    var Portata = resultSet.getString("po.Nome");
                    var piatto = new Dish(codPiatto, nomePiatto, przPorzione, calPorzione, Portata);
                    try (
                            var stat = DAOUtils.prepare(connection, Queries.INGREDIENT_DISH, codPiatto);
                            var ingredientSet = stat.executeQuery()
                    ) {
                        while (ingredientSet.next()) {
                            var ingredient = ingredientSet.getString(1);
                            piatto.addIngredient(ingredient);
                        }
                    } catch (Exception e) {
                        throw new DAOException(e);
                    }
                    try (
                            var stat = DAOUtils.prepare(connection, Queries.ALLERGEN_DISH, codPiatto);
                            var ingredientSet = stat.executeQuery()
                    ) {
                        while (ingredientSet.next()) {
                            var allergen = ingredientSet.getString(1);
                            piatto.addAllergen(allergen);
                        }
                    } catch (Exception e) {
                        throw new DAOException(e);
                    }
                    listOfDishes.add(piatto);
                }
                return listOfDishes;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static void registerDish(final Connection connection, final Dish dish) {
            var dishCode = generateCode();
            final var dishesList = getPiatti(connection).stream().map(Dish::getDishCode).collect(Collectors.toSet());
            while (dishesList.contains(dishCode)) {
                dishCode = generateCode();
            }
            try {
                final var statement = DAOUtils.prepare(connection, Queries.INSERT_DISH, dishCode, dish.getDishName(),
                        dish.getPrezzoPorzione(), dish.getApportoCaloricoPorzione(), dish.getPortata());
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
            for (var ingredient : dish.getIngredienti()) {
                try {
                    final var statement = DAOUtils.prepare(connection, Queries.INSERT_INGREDIENT_OF_DISH,
                            ingredient, dishCode);
                    statement.execute();
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
            for (var allergen : dish.getAllergeni()) {
                try {
                    final var statement = DAOUtils.prepare(connection, Queries.INSERT_ALLERGENS_OF_DISH,
                            allergen, dishCode);
                    statement.execute();
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
        }

        public static void removeDish(final Connection connection, final String dishCode) {
            try{
                final var statement = DAOUtils.prepare(connection, Queries.REMOVE_DISH_ALLERGENS, dishCode);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
            try{
                final var statement = DAOUtils.prepare(connection, Queries.REMOVE_DISH_INGREDIENTS, dishCode);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
            try{
                final var statement = DAOUtils.prepare(connection, Queries.REMOVE_DISH, dishCode);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        private static String generateCode() {
            final var random = new Random();
            return "PIA" + String.valueOf(random.nextInt(NUM_BOUND));
        }
    }
}
