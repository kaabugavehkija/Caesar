package src;

import java.sql.*;
import java.util.HashMap;

public class Database {
    Connection conn = null;

    // Constructor, ehk meetod mis käivitub kohe objekti välja kutsumisel
    public Database() {
        looYhendus();
        looTabel();
    }

    // Et andmebaasi kasutada peame sellega esiteks ühenduse looma
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                          // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:test.db"); // loo ühendus andmebaasi failiga
        } catch ( Exception e ) {                                      // püüa kinni võimalikud errorid
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Opened database successfully");            // lihtsalt meie enda jaoks teade
    }

    // Et andmebaasist kasu oleks, loome uue tabeli. See on nagu uus 'sheet' excelis.
    public void looTabel() {
        // Käsk ise on CREATE TABLE ja sulgude vahel on kõik tulbad, mida tahan, et tabel hoiaks.
        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID INT AUTO_INCREMENT, USERNAME TEXT, " + // jätkub järgmisel real
                "PASSWORD TEXT, FULLNAME TEXT, NUMBER INT, ADDRESS TEXT);";
        teostaAndmebaasiMuudatus(sql);
    }

    // Andmebaasi muudatused ei tagasta väärtusi (erinevalt
    // päringutest) ja on lihtne eraldi meetodi tuua.
    private void teostaAndmebaasiMuudatus(String sql) {
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void teostaAndmebaasiMuudatusPrepared(PreparedStatement preparedStatement) {
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            preparedStatement.executeUpdate();
            preparedStatement.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registreeriKasutaja(String username, String password) {
        // Andmete sisestamiseks on käsk INSERT. Esimestes sulgudes on tulabad KUHU salvestada,
        // teistes sulgudes VALUES() on MIDA salvestada.
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
            // SELECT on nagu excelis hiirega "selekteeriks" mingeid kaste. SQLis tähendab konkreetselt,
            // et milliste tulpade infot soovid kätte saada. WHERE'ga käsed välja võtta ainult tingimustele
            // vastavad väljad.


            // Kuna tegu on päringuga siis käsuks on executeQuery ja ta tagastab andme objekti ResultSet.
            ResultSet rs = stat.executeQuery();
            boolean isValid  = false;
            while (rs.next()){
                String dbPassword = rs.getString("password");
                isValid = password.equals(dbPassword);
                break;
            }

            // Kui Query andmeid ei tagastanud (päring ei toonud tulemusi) siis rs-i kasutada ei saa.
            // Seepärast, kui kasutajat ei eksisteeri tuleb lihtsalt error ja "return" käsuni ei jõutagi.
            // Aga jõutakse lõpu "return false" käsuni küll.

            rs.close();
            stat.close();
            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    // Kui programmis avad ainult ühendusi ja ühtegi ei sulge siis see kulutab arvuti (serveri) ressursse.
    // Üsna kiiresti võib masina kokku jooksutada.
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

    // Kasutaja andmete päring.
    public HashMap getUser(String kasutajanimi) {
        HashMap andmed = new HashMap();
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = ? LIMIT 1;"; // LIMIT piirab tulemuste arvu.
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, kasutajanimi);
            ResultSet rs = stat.executeQuery();
            // Kui stat.executeQuery() toob tagasi tühja tulemuse, siis rs'i kasutada ei saa.

            // Kui oleks mitu rida andmeid, peaks tsükliga lahendama while (rs.next()) {}
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
