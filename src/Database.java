package src;

import java.sql.*;
import java.util.HashMap;

public class Database {
    Connection conn = null;

    // A constructor or a block of code that is similar to a method that’s called when an instance of an object is created.
    public Database() {
        looYhendus();
        looTabel();
    }

    // To use database, we have to make a connection first
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                          // Load driver from sqlite.jar file
            conn = DriverManager.getConnection("jdbc:sqlite:test.db"); // Make connection with database file
        } catch ( Exception e ) {                                      // Catch possible errors
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Opened database successfully");            // Confirmation text about successful connection
    }

    // Creating a new table, which concept is similar to what 'sheet' is in excel.
    public void looTabel() {
        // We'll use command CREATE TABLE and between the parentheses are all columns, which we want the table to hold.
        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID INT AUTO_INCREMENT, USERNAME TEXT, " + // Code continues
                "PASSWORD TEXT, FULLNAME TEXT, NUMBER INT, ADDRESS TEXT);";
        teostaAndmebaasiMuudatus(sql);
    }

    // The changes in database don't return values (unlike to queries)
    // päringutest) and are easy to put in a separate method.
    private void teostaAndmebaasiMuudatus(String sql) {
        try {
            // Statement object is necessary for starting SQL_Login command.
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement has to be closed similarly to Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void teostaAndmebaasiMuudatusPrepared(PreparedStatement preparedStatement) {
        try {
            // Statement object is necessary for starting SQL_Login command.
            preparedStatement.executeUpdate();
            preparedStatement.close(); // Statement has to be closed similarly to Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registreeriKasutaja(String username, String password) {
        // For inserting the data we'll use command INSERT. Between the first paratheses are the columns, WHERE we want
        //  to save and between the second parantheses VALUES() are WHAT we want to save.
        //String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('"+username+"','"+password+"')";
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            stat.setString(2, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teostaAndmebaasiMuudatusPrepared(stat);
    }

    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = ? LIMIT 1;";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            // SELECT is similar to selecting cells withc mouse in excel. In SQL it mmeans, which columns data we want
            // to retrieve. WHERE are the conditions, which have to apply to the retrieved values.


            // Because this is a query, then we have to use command executeQuery, which returns data object ResultSet.
            ResultSet rs = stat.executeQuery();
            boolean isValid  = false;
            while (rs.next()){
                String dbPassword = rs.getString("password");
                isValid = password.equals(dbPassword);
                break;
            }

            // If Query didn't return any data (query finished without results), then it's not possible to use rs.
            // When the user doesn't exist, then it will give an error and "return" command is not used.
            // But "return false" is still executed.

            rs.close();
            stat.close();
            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    // If you only open connections, but don't close any, then it uses a lot of computer's (server's) resource.
    // This way you may quite easily overload the machine.
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

    // User's data query.
    public HashMap getUser(String kasutajanimi) {
        HashMap andmed = new HashMap();
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = ? LIMIT 1;"; // LIMIT restricts the number of results.
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, kasutajanimi);
            ResultSet rs = stat.executeQuery();
            // If stat.executeQuery() didn't return any data (query finished without results),
            // then it's not possible to use rs..

            // If there would be multiple number of rows with data, then cycle should be used while (rs.next()) {}
            while(rs.next()){
                andmed.put("username", rs.getString("username"));
                andmed.put("password", rs.getString("password"));
                andmed.put("fullname", rs.getString("fullname"));
                andmed.put("number", rs.getString("number"));
                andmed.put("address", rs.getString("address"));
                break;
            }
            rs.close();
            stat.close();
            return andmed;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return andmed;
    }

    public void uuendaKasutajaAndmeid(HashMap<String, String> andmed) {
        String username = andmed.get("username");
        String password = andmed.get("password");
        String fullname = andmed.get("fullname");
        Integer number = Integer.parseInt(andmed.get("number"));
        String address = andmed.get("address");

        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, FULLNAME = ?, NUMBER = ?, ADDRESS = ? WHERE USERNAME = ?;";
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            stat.setString(2, password);
            stat.setString(3, fullname);
            stat.setInt(4, number);
            stat.setString(5, address);
            stat.setString(6, username);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        teostaAndmebaasiMuudatusPrepared(stat);
    }
}
