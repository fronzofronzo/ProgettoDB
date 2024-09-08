package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.*;

public class Discount {

    private static final int NUM_BOUND = 99999;
    private final String discountCode;
    private final float value;
    private final boolean used;
    private final int cardNumber;

    public Discount(String discountCode, float value, boolean used, int cardNumber) {
        this.discountCode = discountCode;
        this.value = value;
        this.used = used;
        this.cardNumber = cardNumber;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public float getValue() {
        return value;
    }

    public boolean isUsed() {
        return used;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public static final class DAO {

        public static void generateDiscount(final Connection connection, final Discount discount) {
            var code = generateDiscountCode();
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ALL_DISCOUNTS);
                    final var resultSet = statement.executeQuery()
                    ){
                final Set<String> codesUsed = new HashSet<>();
                while(resultSet.next()) {
                    codesUsed.add(resultSet.getString(1));
                }
                while(codesUsed.contains(code)) {
                    code = generateDiscountCode();
                }
            } catch ( Exception e) {
                throw new DAOException(e);
            }
            final var value = discount.getValue();
            final var used = discount.isUsed();
            final var cardNumber = discount.getCardNumber();
            try {
                final var statement = DAOUtils.prepare(connection, Queries.GENERATE_DISCOUNT,
                        code,value,used,cardNumber);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static List<String> getDiscountsByClient(final Connection connection, final String clientCode) {
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_DISCOUNTS_OF_CLIENT, clientCode);
                    final var resultSet = statement.executeQuery();
            ) {
                final List<String> discounts = new ArrayList<>();
                while (resultSet.next()) {
                    discounts.add(resultSet.getString(1));
                }
                return discounts;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }

    private static String generateDiscountCode() {
        final var random = new Random();
        return "S" + String.valueOf(random.nextInt(NUM_BOUND));
    }
}
