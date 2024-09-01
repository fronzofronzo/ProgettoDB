package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FidelityCard {

    private final int cardNumber;
    private int points;

    public FidelityCard(final int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPoints() {
        return points;
    }

    public static final class DAO {

        public static void addCard(final Connection connection, final int cardNumber) {
           try{
               final var statement = DAOUtils.prepare(connection,Queries.INSERT_CARD, cardNumber);
               statement.execute();
           } catch ( Exception e) {
               throw new DAOException(e);
           }
        }
    }
}
