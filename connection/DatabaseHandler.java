package connection;

import attributes.Stats;
import fighter.Fighter;

import java.sql.*;
import java.util.ArrayList;

/**
 * Singleton class
 * Responsible for connecting to database
 * and executing queries
 * @author hooku
 */
public class DatabaseHandler {

    private static DatabaseHandler instance;
    private static Connection conn = null;
    private static PreparedStatement prepStmt;
    private static ResultSet resSet;

    /**
     * Private constructor
     * Creates connection to database
     * @throws SQLException
     */
    private DatabaseHandler() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafighter" + "?serverTimezone=UTC", "student", "gp7fT61b");
    }

    /**
     * Getter for connection
     * @return conn the connection
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Checks to see if the class has been instantiated
     * @return instance the only instance of the class
     * @throws SQLException
     */
    public static DatabaseHandler getInstance() throws SQLException {
        if (instance == null || instance.getConn().isClosed()) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    /**
     * Execute select statement for all fighters to participate in tournament
     * @param amount the amount of fighters to select
     * @return selectedFighters an ArrayList that holds objects of selected fighters
     * @throws SQLException
     */
    public ArrayList<Fighter> selectFighters(int amount) throws SQLException, Exception {

        prepStmt = conn.prepareStatement("select name, motto, health, strength, resistance_power, speed, wins from fighter limit ?");
        prepStmt.setInt(1, amount);
        resSet = prepStmt.executeQuery();

        ArrayList<Fighter> selectedFighters = new ArrayList<>();
        while(resSet.next()){
            String name = resSet.getString("name");
            String motto = resSet.getString("motto");
            int health = resSet.getInt("health");
            int strength = resSet.getInt("strength");
            int resistancePower = resSet.getInt("resistance_power");
            double speed = resSet.getDouble("speed");
            int wins = resSet.getInt("wins");
            selectedFighters.add(new Fighter(name, motto, strength, resistancePower, speed, wins));
        }
        if(selectedFighters.size() < amount){
            throw new Exception("The amount of fighters available in database are too few to run the tournament");
        }
        resSet.close();
        prepStmt.close();
        return selectedFighters;
    }

    /**
     * Execute update statement for the winning fighter
     * @param fighter the winner of the tournament
     * @throws SQLException
     */
    public int updateFighter(Fighter fighter) throws SQLException{

        prepStmt = conn.prepareStatement("update fighter set wins = ? where name = ?");
        prepStmt.setInt(1, fighter.getWins());
        prepStmt.setString(2, fighter.getName());

        return prepStmt.executeUpdate();
    }

}

