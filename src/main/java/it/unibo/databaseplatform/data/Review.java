package it.unibo.databaseplatform.data;

import it.unibo.databaseplatform.utilities.Pair;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Review {

    private final String dishCode;
    private final String clientCode;
    private final String text;
    private final String rating;

    public Review(final String dishCode, final String clientCode, final String text, final String rating) {
        this.dishCode = dishCode;
        this.clientCode = clientCode;
        this.text = text;
        this.rating = rating;
    }


    public String getDishCode() {
        return this.dishCode;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public String getText() {
        return this.text;
    }

    public String getRating() {
        return this.rating;
    }

    public static final class DAO {

        public static List<Pair<String, Float>> getMostReviewed(final Connection connection) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_MOST_REVIEWED);
                    final var resultSet = statement.executeQuery();
                    ){
                final List<Pair<String,Float>> mostReviewedDishes = new ArrayList<>();
                while(resultSet.next()) {
                    final var name = resultSet.getString(1);
                    final var rating = resultSet.getFloat(2);
                    mostReviewedDishes.add(new Pair<>(name,rating));
                }
                return mostReviewedDishes;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static List<Pair<String, String>> getDishesToReview(final Connection connection,
                                                                   final String clientCode) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_DISHES_TO_REVIEW, clientCode);
                    final var resultSet = statement.executeQuery();
                    ){
                final List<Pair<String, String>> listDishes = new ArrayList<>();
                while(resultSet.next()) {
                    final var pair = new Pair<>(resultSet.getString(1), resultSet.getString(2));
                    listDishes.add(pair);
                }
                return listDishes;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
