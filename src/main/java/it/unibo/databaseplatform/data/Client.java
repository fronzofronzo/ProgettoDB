package it.unibo.databaseplatform.data;

import java.sql.Connection;

public class Client {

    private final String clientCode;
    private final int cardNumber;
    private final String name;
    private final String surname;
    private final int phoneNumber;
    private final String email;
    private final String password;

    public Client(String clientCode, int cardNumber, String name, String surname, int phoneNumber, String email, String password) {
        this.clientCode = clientCode;
        this.cardNumber = cardNumber;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }


    public String getClientCode() {
        return clientCode;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static final class DAO {

        public static Client getFromCode(final Connection connection, final String code, final String password) {
            try(
                    var statement = DAOUtils.prepare(connection, Queries.CLIENT, code, password);
                    var resultSet = statement.executeQuery();
            ){
                Client cliente = null;
                while(resultSet.next()){
                    var clientCode = resultSet.getString("c.CodiceCliente");
                    var cardNumber = resultSet.getInt("c.NumeroCarta");
                    var name = resultSet.getString("c.Nome");
                    var surname = resultSet.getString("c.Cognome");
                    var phoneNumber = resultSet.getInt("c.Telefono");
                    var email = resultSet.getString("c.Email");
                    var passwordReal = resultSet.getString("c.Password");
                    cliente = new Client(clientCode,cardNumber,name,surname,phoneNumber,email,passwordReal);
                }
                return cliente;
            }catch(Exception e) {
                throw new DAOException(e);
            }
        }
    }

}
