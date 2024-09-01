package it.unibo.databaseplatform.data;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Client {

    private static final int CARD_NUMBER_BOUND = 999999;
    private static final int DEFAULT_VALUE = -1;
    private static final int CLIENT_NUMBER_BOUND = 999;


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

        public static String addClient(final Connection connection, final String name, final String surname,
                                       final String phoneNumber, final String email, final String password,
                                       final Address address) {
            final Random random = new Random();
            int cardNumber = DEFAULT_VALUE;
            try(
                final var statement = DAOUtils.prepare(connection, Queries.GET_CARDS_NUMBER);
                final var numbersSet = statement.executeQuery();
            ){
                final Set<Integer> numbers = new HashSet<>();
                while(numbersSet.next()) {
                    numbers.add(numbersSet.getInt(1));
                }

                while(numbers.contains(cardNumber) || cardNumber == DEFAULT_VALUE) {
                    cardNumber = random.nextInt(CARD_NUMBER_BOUND);
                }
                FidelityCard.DAO.addCard(connection,cardNumber);
            } catch (Exception e) {
                throw new DAOException(e);
            }
            String clientCode = "CLI" + random.nextInt(CLIENT_NUMBER_BOUND);
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ALL_CLIENTS_CODE);
                    final var clientsCodeSet = statement.executeQuery();
                    ){
                final Set<String> clientsCode = new HashSet<>();
                while(clientsCodeSet.next()){
                    clientsCode.add(clientsCodeSet.getString(1));
                }
                while(clientsCode.contains(clientCode)) {
                    clientCode = "CLI" + random.nextInt(CLIENT_NUMBER_BOUND);
                }
                try{
                    final var stat = DAOUtils.prepare(connection, Queries.INSERT_CLIENT, clientCode, cardNumber,
                            name,surname, phoneNumber, email, password);
                    stat.execute();
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            } catch (Exception e) {
                throw new DAOException(e);
            }
            String adressCode = "ADR" + random.nextInt(CLIENT_NUMBER_BOUND);
            try(
                    final var statement = DAOUtils.prepare(connection, Queries.GET_ALL_ADDRESSES_CODE);
                    final var adressCodeSet = statement.executeQuery())
            {
                final Set<String> adresses = new HashSet<>();
                while(adressCodeSet.next()) {
                    adresses.add(adressCodeSet.getString(1));
                }
                while(adresses.contains(adressCode)) {
                    adressCode = "ADR" + random.nextInt(CLIENT_NUMBER_BOUND);
                }
            } catch (Exception e) {
                throw new DAOException(e);
            }
            Address.DAO.addAddress(connection,adressCode,address,clientCode);
            return clientCode;
        }
    }

}
