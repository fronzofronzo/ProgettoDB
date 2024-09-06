package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Supply {

    private static final int NUM_BOUND = 99999;

    private final String supplyCode;
    private final Date date;
    private final int quantity;
    private final String adminCode;
    private final String ingredientCode;
    private final String vatNumber;

    public Supply(String supplyCode, Date date, int quantity, String adminCode, String ingredientCode
            , String vatNumber) {
        this.supplyCode = supplyCode;
        this.date = date;
        this.quantity = quantity;
        this.adminCode = adminCode;
        this.ingredientCode = ingredientCode;
        this.vatNumber = vatNumber;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public String getIngredientCode() {
        return ingredientCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public static final class DAO {

        public static void registerSupply(final Connection connection, final Supply supply) {
            var code = generateCode();
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ALL_SUPPLY_CODES);
                    final var resultSet = statement.executeQuery();
                    ){
                final Set<String> alreadyUsed = new HashSet<>();
                while(resultSet.next()) {
                    alreadyUsed.add(resultSet.getString(1));
                }
                while(alreadyUsed.contains(code)) {
                    code = generateCode();
                }
            } catch (Exception e) {
                throw new DAOException(e);
            }
            try{
                final var qta = supply.getQuantity();
                final var admin = supply.getAdminCode();
                final var ingredientCode1 = supply.getIngredientCode();
                final var vat = supply.getVatNumber();
                final var statement = DAOUtils.prepare(connection, Queries.REGISTER_SUPPLY,
                        code, qta, admin, ingredientCode1, vat);
                statement.execute();
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        private static String generateCode() {
            final var random = new Random();
            return 'R' + String.valueOf(random.nextInt(NUM_BOUND));
        }
    }
}