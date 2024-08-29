package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Piatto {

    private final String codicePiatto;
    private final String nomePiatto;
    private final float prezzoPorzione;
    private final int apportoCaloricoPorzione;
    private final String codicePortata;

    public Piatto(String codicePiatto, String nomePiatto, float prezzoPorzione, int apportoCaloricoPorzione, String codicePortata){
        this.codicePiatto = codicePiatto;
        this.nomePiatto = nomePiatto;
        this.prezzoPorzione = prezzoPorzione;
        this.apportoCaloricoPorzione = apportoCaloricoPorzione;
        this.codicePortata = codicePortata;
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

    public String getCodicePortata() {
        return this.codicePortata;
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
                    var codPortata = resultSet.getString("p.CodicePortata");
                    var piatto = new Piatto(codPiatto,nomePiatto,przPorzione,calPorzione,codPortata);
                    listOfDishes.add(piatto);
                }
                return listOfDishes;
            }catch (Exception e){
                throw new DAOException(e);
            }
        }
    }
}
