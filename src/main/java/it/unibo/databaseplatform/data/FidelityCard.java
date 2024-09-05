package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FidelityCard {

    private final int cardNumber;
    private int points;

    public FidelityCard(final int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardPoints(final int points) {
        this.points = points;
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

        public static void addPointsToCard(final Connection connection, final int cardNumber, final int points) {
            try{
                final var statement = DAOUtils.prepare(connection, Queries.ADD_POINTS_TO_CARD, points, cardNumber);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static FidelityCard getFidelityCard(final Connection connection, final String clientCode) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_CARD_INFORMATION, clientCode);
                    final var resultSet = statement.executeQuery();
                    ){
                resultSet.next();
                final var cardNumber = resultSet.getInt(1);
                final var cardPoints = resultSet.getInt(2);
                final var fidelityCard = new FidelityCard(cardNumber);
                fidelityCard.setCardPoints(cardPoints);
                return  fidelityCard;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
