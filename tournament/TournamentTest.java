package tournament;

import fighter.Fighter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    private static Tournament tour;
    private Fighter fighter1;
    private Fighter fighter2;

    @BeforeAll
    static void setUpAll() throws SQLException {
        tour = Tournament.getInstance();
    }

    @BeforeEach
    void setUp(){
        fighter1 = new Fighter("Fighter1", "Test", 50, 20, 0.1, 0);
        fighter2 = new Fighter("Fighter2", "Test", 50, 20, 0.1, 0);
        tour.setRemainingFighters(new ArrayList<>(List.of(fighter1, fighter2)));
    }

    @Test
    void getInstance_ShouldNeverReturnNull() {
        assertNotNull(tour);
    }


    @Test
    void getNextFighter_ShouldReturnFirstElement() {
        assertSame(fighter1, tour.getNextFighter());
    }

    @Test
    void getNextFighter_ShouldDecreaseArrayListByOne(){
        int sizeBefore = tour.getRemainingFighters().size();
        tour.getNextFighter();
        assertEquals(sizeBefore - 1, tour.getRemainingFighters().size());
    }

    @Test
    void addMatchWinner_ShouldIncreaseArrayListByOne() {
        int sizeBefore = tour.getRemainingFighters().size();
        Fighter fighter3 = new Fighter("Fighter3", "Test", 50, 20, 0.1, 0);
        tour.addMatchWinner(fighter3);
        assertEquals(sizeBefore + 1, tour.getRemainingFighters().size());
    }

    @Test
    void updateTournamentTree() {
        //Hur testa denna? Kolla om det blir fler fighters i arrayen. Måste dock felhantera om man skickar in när den redan är full.
        //Odynamiskt tree, vilket kommer försvinna om grafik läggs till.
    }
}