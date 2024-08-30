package it.unibo.databaseplatform.data;

public class Clients {

    private final String clientCode;
    private final int cardNumber;
    private final String name;
    private final String surname;
    private final int phoneNumber;
    private final String email;
    private final String password;

    public Clients(String clientCode, int cardNumber, String name, String surname, int phoneNumber, String email, String password) {
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
}
