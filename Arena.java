import controller.Controller;
import fighter.Fighter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds the tournament logic.
 * Calls the controller for input and output.
 */

public class Arena {

    private Controller controller;
    private ArrayList<Fighter> match;

    /**
     * Constructor
     * Instantiate ArrayList match, which will hold two fighters for current match
     * @param controller is used to communicate with view and model
     */
    public Arena(Controller controller) {
        this.controller = controller;
        this.match = new ArrayList<>();
    }

    /**
     * Perform tournament logic.
     * One loop represents one match and will continue as long as there are fighters in the tournament
     * One match consists of three rounds which include one attack with following defence from each fighter
     * @throws SQLException
     */
    public void runTournament() throws SQLException, Exception {
        controller.eventBeforeTour();

        while (controller.getRemainingFighters().size() > 1) {
            match = controller.eventBeforeMatch();
            for (int i = 0; i < 3; i++) {
                controller.eventBeforeBattle(i + 1);
                Collections.shuffle(match);
                boolean proceed;

                proceed = controller.battle(match.get(0), match.get(1));
                if (!proceed) {
                    break;
                }
                proceed = controller.battle(match.get(1), match.get(0));
                if (!proceed) {
                    break;
                }
                i = controller.eventAfterBattle(i, match.get(0), match.get(1));
            }
            controller.eventAfterMatch(match.get(0), match.get(1));
        }
        controller.eventAfterTournament();

    }
}
