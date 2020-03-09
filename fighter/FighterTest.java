package fighter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
    void attack() {
        //Testa så att returvärdet inte är mindre än -10% eller större än +10%
    }

    @Test
    void defend() {
        //Testa så att returvärdet inte är mindre än -10% eller större än +10%
    }

    @Test
    void escapedHit() {
        //Testa så att false om speed är 0.
    }

    @Test
    void isDead() {
        //Testa så att true vid negativa värden och false vid positiva värden
    }

    @Test
    void wonAgainst() {
        //Testa fler värden på att fighter1 vinner (true).
        //Testa fler värden på att fighter1 inte vinner (false).
        fighter1.getHealth().setPower(5);
        fighter2.getHealth().setPower(110);
        assertFalse(fighter1.wonAgainst(fighter2));
        assertTrue(fighter2.wonAgainst(fighter1));

        fighter1.getHealth().setPower(50);
        fighter2.getHealth().setPower(50);
        assertFalse(fighter1.wonAgainst(fighter2));
        assertFalse(fighter2.wonAgainst(fighter1));
    }
}