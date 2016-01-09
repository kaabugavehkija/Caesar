package src.test;

import org.junit.Test;
import src.Database;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by rene on 30.12.2015.
 */
public class DatabaseTest {

    @Test
    public void testGetUser() throws Exception {
        Database database = new Database();
        HashMap user = database.getUser("test");
        System.out.println("password=" + user.get("password"));

    }

    @Test
    public void testLogin() throws Exception {
        Database database = new Database();
        boolean login = database.login("test", "p");
        assertEquals(true, login);
    }

    @Test
    public void testRegistreeriKasutaja() throws Exception {
        String user = "reg";
        String pass = "kas";
        Database database = new Database();
        database.registreeriKasutaja(user, pass);
        boolean login = database.login(user, pass);
        assertEquals(true, login);
    }

    @Test
    public void testUuendaKasutajaAndmeid() throws Exception {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("username", "test");
        data.put("password", "test2");
        data.put("fullname", "full");
        data.put("number", "1");
        data.put("address", "adr");
        Database database = new Database();
        database.uuendaKasutajaAndmeid(data);
        boolean login = database.login("test", "test2");
        assertEquals(true, login);
    }
}