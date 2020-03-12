package tournament;

import connection.DatabaseHandler;
import fighter.Fighter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Singleton class
 * Holds information about the tournament
 * and communicates with the database that holds fighters
 */
public class Tournament {
    private static Tournament instance;

    private DatabaseHandler db;
    private ArrayList<Fighter> remainingFighters;
    private Fighter[] tournamentTree;

    /**
     * Private constructor
     * Gets instance of DatabaseHandler
     * Instantiate ArrayList of remaining fighters
     * Instantiate array of participating fighters, one element for each cell in the tournament tree
     * @throws SQLException
     */
    private Tournament() throws SQLException {
        this.db = DatabaseHandler.getInstance();
        this.remainingFighters = new ArrayList<>();
        this.tournamentTree = new Fighter[31];
    }

    /**
     * Check to see if the class has been instantiated
     * @return instance the only instance of Tournament class
     * @throws SQLException
     */
    public static Tournament getInstance() throws SQLException {
        if(instance == null){
            instance = new Tournament();
        }
        return instance;
    }

    /**
     * Getter for remainingFighters
     * @return remainingFighters the fighters left in the tournament
     */
    public ArrayList<Fighter> getRemainingFighters() {
        return remainingFighters;
    }

    /**
     * Setter for remainingFighters
     * @param remainingFighters ArrayList of fighters
     */
    public void setRemainingFighters(ArrayList<Fighter> remainingFighters) {
        this.remainingFighters = remainingFighters;
    }

    /**
     * Getter for tournamentTree
     * @return tournamentTree the array used to create tournament tree
     */
    public Fighter[] getTournamentTree() {
        return tournamentTree;
    }

    /**
     * Picks and removes the first element of remainingFighters
     * @return nextFighter a fighter for upcoming match
     */
    public Fighter getNextFighter(){
        Fighter nextFighter = remainingFighters.get(0);
        remainingFighters.remove(0);
        return nextFighter;
    }

    /**
     * Select 16 fighters from database and put them in ArrayList remainingFighters
     * Shuffle to create randomized match order
     * Fills tournamentTree array with all participating fighters
     * @throws SQLException
     */
    public void createTournament() throws Exception {
        final int FIGHTERS_TO_SELECT = 16;
        remainingFighters = db.selectFighters(FIGHTERS_TO_SELECT);

        Collections.shuffle(remainingFighters);

        for (Fighter remainingFighter : remainingFighters) {
            updateTournamentTree(remainingFighter);
        }
    }

    /**
     * Sends the tournament winner to database to store number of wins
     * @param winner the winner
     * @throws SQLException
     */
    public void updateTourWinner(Fighter winner) throws SQLException {
        db.updateFighter(winner);
    }

    /**
     * Adds the winner of last match to ArrayList remainingFighters
     * @param winner the winner of last match
     */
    public void addMatchWinner(Fighter winner){
        remainingFighters.add(winner);
    }

    /**
     * Adds a new fighter to the next empty element/cell in the torunamentTree array
     * @param fighter the fighter add
     */
    public void updateTournamentTree(Fighter fighter){
        for(int i = 0; i < tournamentTree.length; i++){
            if(tournamentTree[i] == null){
                tournamentTree[i] = fighter;
                break;
            }
        }
    }
}
