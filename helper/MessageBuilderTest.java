package helper;

import fighter.Fighter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBuilderTest {

    private Fighter fighter1;
    private Fighter fighter2;

    @BeforeEach
    void setUp(){
        fighter1 = new Fighter("Fighter1", "Test1", 50, 20, 0.1, 0);
        fighter2 = new Fighter("Fighter2", "Test1", 50, 20, 0.1, 0);
    }
    //Testa alla genom att kolla att returvärde inte är null (?) Alla returnerar en String.
    @Test
    void pressButton_NotNull() {
        assertNotNull(MessageBuilder.pressButton());
    }

    @Test
    void matchLevel_NotNull() {
        for(int i = 0; i < 20; i++){
            assertNotNull(MessageBuilder.matchLevel(i));
        }

    }

    @Test
    void matchIntro_NotNull() {
        assertNotNull(MessageBuilder.matchIntro(fighter1, fighter2));
    }

    @Test
    void battleRound_NotNull() {
        for(int i = 1; i <= 3; i++){
            assertNotNull(MessageBuilder.battleRound(i));
        }
    }

    @Test
    void attackMessage_NotNull() {
        assertNotNull(MessageBuilder.attackMessage(fighter1, 70));
    }

    @Test
    void escapeMessage_NotNull() {
        assertNotNull(MessageBuilder.escapedMessage(fighter1));
    }

    @Test
    void defendMessage_NotNull() {
        assertNotNull(MessageBuilder.defendMessage(fighter1, 50, 10));
    }

    @Test
    void deadMessage_NotNull() {
        assertNotNull(MessageBuilder.deadMessage(fighter1));
    }

    @Test
    void battleRoundSummary_NotNull() {
        assertNotNull(MessageBuilder.battleRoundSummary(fighter1, fighter2));
    }

    @Test
    void extraFightRound_NotNull() {
        assertNotNull(MessageBuilder.extraFightRound());
    }

    @Test
    void matchWinner_NotNull() {
        assertNotNull(MessageBuilder.matchWinner(fighter1, fighter2));
    }

    @Test
    void tournamentWinner_NotNull() {
        assertNotNull(MessageBuilder.tournamentWinner(fighter1));
    }

    @Test
    void fighterProfile_NotNull() {
        assertNotNull(MessageBuilder.fighterProfile(fighter1));
    }

    @Test
    void tree_NotNull()  {
        int[] testCases = {2,31};
        for (int testCase : testCases) {
            Fighter[] fighters = new Fighter[testCase];
            assertNotNull(MessageBuilder.tree(fighters));
        }

    }
}