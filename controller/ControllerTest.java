package controller;

import fighter.Fighter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tournament.Tournament;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private static Controller controller;
    private static Tournament tour;
    private Fighter attacker;
    private Fighter defender;

    @BeforeAll
    static void setUpAll() throws SQLException {
        controller = new Controller();
        tour = Tournament.getInstance();
    }

    @BeforeEach
    void setUp(){
        attacker = new Fighter("Attacker", "Test", 50, 20, 0.1, 0);
        defender = new Fighter("Defender", "Test", 50, 20, 0.1, 0);
    }

    @Test
    void eventBeforeMatch_TwoElements() {
        tour.setRemainingFighters(new ArrayList<>(List.of(attacker, defender)));
        assertEquals(2, controller.eventBeforeMatch().size());
    }

    @Test
    void battle_DefenderDead_False() {
        defender.getHealth().setPower(0);
        assertFalse(controller.battle(attacker, defender));
    }

    @Test
    void battle_DefenderAlive_True(){
        defender.getHealth().setPower(1000);
        assertTrue(controller.battle(attacker, defender));
    }


    @Test
    void eventAfterBattle_LastRoundDraw_PreviousRound() {
        assertEquals(1, controller.eventAfterBattle(2, attacker, defender));
    }

    @Test
    void eventAfterBattle_LastRoundNotDraw_CurrentRound(){
        attacker.getHealth().setPower(50);
        defender.getHealth().setPower(60);
        assertEquals(2, controller.eventAfterBattle(2, attacker, defender));
    }

    @Test
    void eventAfterBattle_FirstRoundDraw_CurrentRound(){
        assertEquals(0, controller.eventAfterBattle(0, attacker, defender));
    }

}