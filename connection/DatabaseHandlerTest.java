package connection;

import fighter.Fighter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseHandlerTest {

    private static DatabaseHandler instance;
    private static ArrayList<Fighter> fighters;
    private static Fighter fighter;
    private PreparedStatement prepStmt;
    private static Connection conn;

    @BeforeAll
    static void setUp() throws SQLException {
        instance = DatabaseHandler.getInstance();
        conn = instance.getConn();
        fighters = new ArrayList<>();
        fighter = new Fighter("Test", "Test", 50, 30, 0.2, 2);
    }

    @Test
    void getInstance_ShouldNotReturnNull(){
        assertNotNull(instance);
    }

    @Test
    void selectFighters_nrEqualOrLessThanAvailableFighters_ShouldReturnSelectedAmount() throws SQLException, Exception {
        int nrOfFighters = 16;
        fighters = instance.selectFighters(nrOfFighters);
        assertEquals(nrOfFighters, fighters.size());
    }

    @Test
    void selectFighters_nrHigherThanAvailableFighters_ShouldThrowException(){
        int nrOfFighters = 18;
        Assertions.assertThrows(Exception.class, (() -> instance.selectFighters(nrOfFighters)));
    }

    @Test
    void updateFighterTest() throws SQLException {
        prepStmt = conn.prepareStatement("insert into fighter(name, motto, health, strength, resistance_power, speed, wins) values (?,?,?,?,?,?,?)");
        prepStmt.setString(1, fighter.getName());
        prepStmt.setString(2, fighter.getMotto());
        prepStmt.setDouble(3, fighter.getHealth().getPower());
        prepStmt.setDouble(4, fighter.getStrength().getPower());
        prepStmt.setDouble(5, fighter.getResPow().getPower());
        prepStmt.setDouble(6, fighter.getSpeed().getPower());
        prepStmt.setInt(7, fighter.getWins());
        prepStmt.execute();

        assertEquals(1, instance.updateFighter(fighter));

        prepStmt = conn.prepareStatement("delete from fighter where name = ?");
        prepStmt.setString(1, fighter.getName());
        prepStmt.execute();
    }
}