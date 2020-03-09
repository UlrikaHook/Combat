package controller;

import fighter.Fighter;
import tournament.Tournament;
import view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gets data from model (Tournament, which holds fighters)
 * Sends information for output to View.
 */
public class Controller {

    private Tournament tour;
    private Scanner scan;

    /**
     * Constructor.
     * Gets instance from Tournament(Singleton)
     * @throws SQLException
     */
    public Controller() throws SQLException {
        tour = Tournament.getInstance();
        scan = new Scanner(System.in);
    }

    /**
     * Getter for model data
     * @return remainingFighters from Tournament, the list of fighters left in the tournament
     */
    public ArrayList<Fighter> getRemainingFighters(){
        return tour.getRemainingFighters();
    }

    /**
     * Calls model to create tournament
     * Loops through fighters in the tournament and sends it to view
     * Sends fighters to tree class to print tournament tree view.
     * @throws SQLException
     */
    public void eventBeforeTour() throws SQLException {
        tour.createTournament();
        String message;
        for(int i = 0; i < getRemainingFighters().size(); i++) {
            Fighter f = getRemainingFighters().get(i);
            message = MessageBuilder.fighterProfile(f);
            View.printMessage(message);
        }
        message = MessageBuilder.pressButton();
        View.printMessage(message);
        scan.nextLine();

        message = MessageBuilder.tree(tour.getTournamentTree());
        View.printMessage(message);
    }

    /**
     * Sends number of fighters left to MessageBuilder, to print match level in View
     * Calls model to get fighters for next match
     * Sends picked fighters to View, to print match intro.
     * @return match an arrayList with two fighters for next match
     */
    public ArrayList<Fighter> eventBeforeMatch() {
        String message = MessageBuilder.matchLevel(getRemainingFighters().size());
        View.printMessage(message);
        ArrayList<Fighter> match = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            match.add(tour.getNextFighter());
        }
        message = MessageBuilder.matchIntro(match.get(0), match.get(1));
        View.printMessage(message);
        return match;
    }

    /**
     * Sends battle round to View
     * @param round the round in current fight.
     */
    public void eventBeforeBattle(int round){
        String message = MessageBuilder.battleRound(round);
        View.printMessage(message);
    }

    /**
     * Hold logic for battle.
     * Calls attack method - Attack message in View
     * Calls escape method - Escape message in View if defender escaped
     * Calls defend method - Defend message in View if defender didn't escape
     * @param attacker the fighter to attack
     * @param defender the fighter to defend
     * @return false if defender is dead, true if defender is alive.
     */
    public boolean battle(Fighter attacker, Fighter defender){
        int hit = attacker.attack();
        String message = MessageBuilder.attackMessage(attacker, hit);
        View.printMessage(message);

        if (defender.escapedHit()) {
            message = MessageBuilder.escapedMessage(defender);
            View.printMessage(message);
        } else {
            int resistance = defender.defend(hit);
            message = MessageBuilder.defendMessage(defender, resistance, hit);
            View.printMessage(message);

            if(defender.isDead()){
                message = MessageBuilder.deadMessage(defender);
                View.printMessage(message);
                return false;
            }
        }
        return true;
    }

    /**
     * Sends fighter to MessageBuilder, to print their current status in View
     * Checks if the fighters health are equal after third round. If true round number decreases by one.
     * @param i the current round
     * @param f1 fighter 1
     * @param f2 fighter 2
     * @return i the next round
     */
    public int eventAfterBattle(int i, Fighter f1, Fighter f2){
        String message = MessageBuilder.battleRoundSummary(f1, f2);
        View.printMessage(message);

        if(i == 2 && f1.getHealth().getPower() == f2.getHealth().getPower()){
            message = MessageBuilder.extraFightRound();
            View.printMessage(message);
            i--;
        }
        message = MessageBuilder.pressButton();
        View.printMessage(message);
        scan.nextLine();

        return i;
    }

    /**
     * Determines who won the fight
     * Sends winner to model to update fighter arrayList and tounament tree.
     * Sends winner to View
     * Reset both fighters health
     * Send tournament tree to View
     * @param f1 fighter 1
     * @param f2 fighter 2
     */
    public void eventAfterMatch(Fighter f1, Fighter f2){

        Fighter winner = f1.wonAgainst(f2) ? f1 : f2;
        Fighter loser = f1.wonAgainst(f2) ? f2 : f1;
        tour.addMatchWinner(winner);
        tour.updateTournamentTree(winner);
        String message = MessageBuilder.matchWinner(loser, winner);
        View.printMessage(message);

        f1.getHealth().resetHealth();
        f2.getHealth().resetHealth();

        message = MessageBuilder.pressButton();
        View.printMessage(message);
        scan.nextLine();

        message = MessageBuilder.tree(tour.getTournamentTree());
        View.printMessage(message);
    }

    /**
     * Determines the winner of the tournament
     * Increase the fighters wins by one
     * Sends wins to database
     * Sends winner to View.
     * @throws SQLException
     */
    public void eventAfterTournament() throws SQLException {
        Fighter winner = tour.getRemainingFighters().get(0);
        winner.addWin();
        tour.updateTourWinner(winner);
        String message = MessageBuilder.tournamentWinner(winner);
        View.printMessage(message);
    }

}
