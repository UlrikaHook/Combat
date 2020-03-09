package connection;

import fighter.Fighter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseHandlerTest {

    private static DatabaseHandler instance;
    private static ArrayList<Fighter> fighters;
    private static Fighter fighter;

    @BeforeAll
    static void setUp() throws SQLException {
        instance = DatabaseHandler.getInstance();
        fighters = new ArrayList<>();
        fighter = new Fighter("Test", "Test", 50, 30, 0.2, 1);
    }

    @Test
    void DBConnectionTest(){
        assertNotNull(instance);
    }

    @Test
    void selectFightersTest() throws SQLException {
        //Testa så att storleken på arraylist stämmer överens med antalet valda fighters. Prova fler mha arraylist. Testa t ex 0.
        //Hantera exception för om fler fighters väljs än vad som finns i databasen
        //Bryt ut sql-satserna?
        int nrOfFighters = 18;
        fighters = instance.selectFighters(nrOfFighters);
        assertEquals(nrOfFighters, fighters.size());
    }

    @Test
    void updateFighterTest() throws SQLException {
        //önskvärt hämta samma fighter från databasen för att se att all data stämmer.
        //Testa i första hand att antalet uppdaterade rader stämmer med det önskvärda (1).
        assertEquals(1, instance.updateFighter(fighter));
    }
}