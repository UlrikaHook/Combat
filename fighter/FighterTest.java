package fighter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterTest {

    private Fighter fighter1;
    private Fighter fighter2;

    @BeforeEach
    void setUp(){
        fighter1 = new Fighter("Test", "Test", 50, 20, 0.2, 0);
        fighter2 = new Fighter("Test2", "Test2", 40, 30, 0.1, 1);
    }

    @Test
    void attack_StrengthPlusMinusTenPercent() {
        double strength = fighter1.getStrength().getPower();
        for(int i = 0; i < 50; i++){
            assertTrue(fighter1.attack() >= strength * 0.9 && fighter1.attack() <= strength * 1.1);
        }

    }

    @Test
    void defend_ResistancePlusMinusTenPercent() {
        double resistance = fighter1.getResPow().getPower();
        for(int i = 0; i < 50; i++){
            assertTrue(fighter1.defend(50) >= resistance * 0.9 && fighter1.defend(50) <= resistance * 1.1);
        }
    }

    @Test
    void escapedHit_SpeedEqualToZero_False() {
        fighter1.getSpeed().setPower(0);
        assertFalse(fighter1.escapedHit());
    }

    @Test
    void isDead_HealthZeroOrNegative_True() {
        int[] testCases = {0,-10, -50};
        for(int i = 0; i < testCases.length; i++){
            fighter1.getHealth().setPower(i);
            assertTrue(fighter1.isDead());
        }
    }

    @Test
    void isDead_HealthPositive_False(){
        int[] testCases = {1, 20, 60, 120};
        for (int testCase : testCases) {
            fighter1.getHealth().setPower(testCase);
            assertFalse(fighter1.isDead());
        }
    }

    @Test
    void wonAgainst_HealthHigherThanOpponent_True() {
        fighter1.getHealth().setPower(50);
        fighter2.getHealth().setPower(20);
        assertTrue(fighter1.wonAgainst(fighter2));
    }

    @Test
    void wonAgainst_HealthEqualOrLowerThanOpponent_False(){
        fighter1.getHealth().setPower(20);
        fighter2.getHealth().setPower(50);
        assertFalse(fighter1.wonAgainst(fighter2));
        fighter2.getHealth().setPower(20);
        assertFalse(fighter1.wonAgainst(fighter2));
    }
}