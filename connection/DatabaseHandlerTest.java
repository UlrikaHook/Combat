package connection;

import fighter.Fighter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    void getInstance_NotNull(){
        assertNotNull(instance);
    }

    @Test
    void selectFighters_SelectedAmountAvailable_SelectedAmount() throws Exception {
        int[] testCases = {1, 15, 16};
        for (int testCase : testCases) {
            fighters = instance.selectFighters(testCase);
            assertEquals(testCase, fighters.size());
        }
    }

    @Test
    void selectFighters_SelectedAmountTooMany_ExceptionThrown(){
        int nrOfFighters = 18;
        Assertions.assertThrows(Exception.class, (() -> instance.selectFighters(nrOfFighters)));
    }

    @Test
    void updateFighter_OneRowAffected() throws SQLException {
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