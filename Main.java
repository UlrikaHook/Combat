import controller.Controller;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        Controller controller = new Controller();

        Arena arena = new Arena(controller);
        arena.runTournament();


    }

}
