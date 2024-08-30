package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Piatto {

    private final String codicePiatto;
    private final String nomePiatto;
    private final float prezzoPorzione;
    private final int apportoCaloricoPorzione;
    private final String portata;
    private final List<String> allergeni;
    private final List<String> ingredienti;

    public Piatto(String codicePiatto, String nomePiatto, float prezzoPorzione, int apportoCaloricoPorzione,
                  String portata){
        this.codicePiatto = codicePiatto;
        this.nomePiatto = nomePiatto;
        this.prezzoPorzione = prezzoPorzione;
        this.apportoCaloricoPorzione = apportoCaloricoPorzione;
        this.portata = portata;
        this.allergeni = new LinkedList<>();
        this.ingredienti = new LinkedList<>();
    }

    public String getCodicePiatto(){
        return this.codicePiatto;
    }

    public String getNomePiatto(){
        return this.nomePiatto;
    }

    public float getPrezzoPorzione(){
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

    private void addIngredient(String ing) {
        this.ingredienti.add(ing);
    }

    private void addAllergen(String all) {
        this.allergeni.add(all);
    }

    public static final class DAO {

        public static List<Piatto> getPiatti(Connection connection) {
            try(
                    var statement = DAOUtils.prepare(connection, Queries.ALL_DISHES);
                    var resultSet = statement.executeQuery();
            ){
                final List<Piatto> listOfDishes = new ArrayList<>();
                while(resultSet.next()){
                    var codPiatto = resultSet.getString("p.CodicePiatto");
                    var nomePiatto = resultSet.getString("p.NomePiatto");
                    var przPorzione = resultSet.getFloat("p.PrezzoPorzione");
                    var calPorzione = resultSet.getInt("p.ApportoCaloricoPorzione");
                    var Portata = resultSet.getString("po.Nome");
                    var piatto = new Piatto(codPiatto,nomePiatto,przPorzione,calPorzione,Portata);
                    try(
                            var stat = DAOUtils.prepare(connection, Queries.INGREDIENT_DISH, codPiatto);
                            var ingredientSet = stat.executeQuery()
                    ) {
                        while(ingredientSet.next()) {
                            var ingredient = ingredientSet.getString(1);
                            piatto.addIngredient(ingredient);
                        }
                    }catch (Exception e) {
                        throw new DAOException(e);
                    }
                    try(
                            var stat = DAOUtils.prepare(connection, Queries.ALLERGEN_DISH, codPiatto);
                            var ingredientSet = stat.executeQuery()
                    ) {
                        while(ingredientSet.next()) {
                            var allergen = ingredientSet.getString(1);
                            piatto.addAllergen(allergen);
                        }
                    }catch (Exception e) {
                        throw new DAOException(e);
                    }
                    listOfDishes.add(piatto);
                }
                return listOfDishes;
            }catch (Exception e){
                throw new DAOException(e);
            }
        }
    }
}
