package it.unibo.databaseplatform.data;

import java.sql.Connection;

public class Admin {

    private final String adminCode;
    private final String name;
    private final String surname;
    private final String phone;
    private final String email;
    private final String password;


    public Admin(String adminCode, String name, String surname, String phone, String email, String password) {
        this.adminCode = adminCode;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static final class DAO {

        public static Admin getAdmin(final Connection connection, final String adminCode, final String password) {
            try(
                    final var statement = DAOUtils.prepare(connection,Queries.GET_ADMIN, adminCode,password);
                    final var resultSet = statement.executeQuery();
                    ){
                Admin admin = null;
                while (resultSet.next()) {
                    final var code = resultSet.getString(1);
                    final var name = resultSet.getString(2);
                    final var surname = resultSet.getString(3);
                    final var phone = resultSet.getString(4);
                    final var email = resultSet.getString(5);
                    final var pass = resultSet.getString(6);
                    admin = new Admin(code, name, surname, phone, email, pass);
                }
                return admin;
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
