package controller;

import fighter.Fighter;
import org.junit.jupiter.api.Assertions;
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
    void pressButton_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.pressButton());
    }

    @Test
    void matchLevel_ShouldNotReturnNull() {
        for(int i = 0; i < 20; i++){
            assertNotNull(MessageBuilder.matchLevel(i));
        }

    }

    @Test
    void matchIntro_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.matchIntro(fighter1, fighter2));
    }

    @Test
    void battleRound_ShouldNotReturnNull() {
        for(int i = 1; i <= 3; i++){
            assertNotNull(MessageBuilder.battleRound(i));
        }
    }

    @Test
    void attackMessage_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.attackMessage(fighter1, 70));
    }

    @Test
    void escapeMessage_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.escapedMessage(fighter1));
    }

    //Vore önskvärt kunna kontrollera att rätt meddelande skickas tillbaka beroende på differensen mellan hit och resistance
    @Test
    void defendMessage_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.defendMessage(fighter1, 50, 10));
    }

    @Test
    void deadMessage_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.deadMessage(fighter1));
    }

    @Test
    void battleRoundSummary_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.battleRoundSummary(fighter1, fighter2));
    }

    @Test
    void extraFightRound_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.extraFightRound());
    }

    @Test
    void matchWinner_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.matchWinner(fighter1, fighter2));
    }

    @Test
    void tournamentWinner_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.tournamentWinner(fighter1));
    }

    @Test
    void fighterProfile_ShouldNotReturnNull() {
        assertNotNull(MessageBuilder.fighterProfile(fighter1));
    }

    @Test
    void tree_TooShortArray_ShouldNotReturnNull()  {
            Fighter[] fighters = new Fighter[2];
            assertNotNull(MessageBuilder.tree(fighters));
    }

    @Test
    void tree_ExpectedArrayLength_ShouldNotReturnNull(){
        Fighter[] fighters = new Fighter[31];
        assertNotNull(MessageBuilder.tree(fighters));
    }
}