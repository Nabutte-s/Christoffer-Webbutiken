import Customer.CustomerController;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        Login.LoginController loginController = new Login.LoginController();
        loginController.run();
/*

        CustomerController customerController = new CustomerController();
        customerController.run();
*/

    }
}