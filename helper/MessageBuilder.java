package helper;

import fighter.Fighter;


/**
 * Responsible of printing tournament tree
 *
 * @author hooku
 */
public class MessageBuilder {

    /**
     * Constants to be used in to create message
     */
    private static final String SMALL_TAB = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    private static final String MEDIUM_TAB = SMALL_TAB + "\t";
    private static final String BIG_TAB = MEDIUM_TAB + "\t\t";

    private static final String LINE = "____________";
    private static final String CURVES = "~~~~~~~~~~~~";
    private static final String R_ARROWS = ">>>>>>>>>>>>>>";
    private static final String L_ARROWS = "<<<<<<<<<<<<<<";

    /**
     * Constants used for creating tournament tree
     */
    private static final int WIDTH = 12;
    private static final String MINUS = "--------------";
    private static final String FRAME = "+------------+";
    private static final String STAR_FRAME = "+************+";
    private static final String WINNER_LINE = "      ||      ";
    private static final String R_WALL = "             |";
    private static final String L_WALL = "|             ";
    private static final String S_R_WALL = "                           |";
    private static final String S_L_WALL = "|                           ";
    private static final String SPACE = "              ";


    /**
     * Creates String to be printed when user is controlling when to continue
     *
     * @return message to user
     */
    public static String pressButton() {
        return BIG_TAB + "Press enter to continue";
    }

    /**
     * Method used within the class. Creates headers.
     *
     * @param text     the text to be printed in header
     * @param width    the maximun width of the text in current category
     * @param rSymbols the symbols to the right of the text
     * @param lSymbols the symbols to the left of the text
     * @return header the created header
     */
    private static String header(String text, int width, String rSymbols, String lSymbols) {
        int headerLength = text.length();
        String header = rSymbols;
        for (int i = 0; i < (width - headerLength) / 2; i++) {
            header += " ";
        }
        header += text;
        for (int i = 0; i < (width - headerLength) / 2; i++) {
            header += " ";
        }
        header += lSymbols;
        return header;
    }

    /**
     * Creates String of match level dependent on number of remaining fighters
     *
     * @param nrOfFighters fighters remaining in tournament
     */
    public static String matchLevel(int nrOfFighters) {
        String text = "";
        switch (nrOfFighters) {
            case 16:
                text = " ROUND 1 ";
                break;
            case 8:
                text = " QUARTER FINALS ";
                break;
            case 4:
                text = " SEMIFINAL ";
                break;
            case 2:
                text = " THE BIG FINAL ";
                break;
            default:
                text = "~~~~~~~~~~~~";
        }
        return SMALL_TAB + header(text, 16, CURVES + CURVES, CURVES + CURVES) + "\n";
    }

    /**
     * Prints information about the fighter to fight upcoming round
     *
     * @param f1 fighter 1
     * @param f2 fighter 2
     */
    public static String matchIntro(Fighter f1, Fighter f2) {
        String text = " " + f1 + " VS. " + f2 + " ";
        return MEDIUM_TAB + header(text, 27, R_ARROWS, L_ARROWS) + "\n";
    }

    /**
     * Creates message of upcoming battle  round
     *
     * @param round the upcoming battle round
     */
    public static String battleRound(int round) {
        String text = " Fight round NO. " + round + " ";
        return MEDIUM_TAB + header(text, 27, CURVES, CURVES);

    }

    /**
     * Creates message during attack
     *
     * @param attacker the attacker
     * @param hit      the power of the attack
     */
    public static String attackMessage(Fighter attacker, int hit) {
        return BIG_TAB + attacker + " attacks! Hits with a power of  " + hit;
    }

    /**
     * Creates message when defender escapes
     *
     * @param defender the defender
     */
    public static String escapedMessage(Fighter defender) {
        return BIG_TAB + defender + " escapes the hit\n";
    }

    /**
     * Creates message during defence
     *
     * @param defender   the defender
     * @param resistance the power of resistance
     * @param hit        the power of the hit
     */
    public static String defendMessage(Fighter defender, int resistance, int hit) {
        return BIG_TAB + defender + " responds with resistance power of " + resistance + "\n" +
                BIG_TAB + ((hit - resistance <= 0) ? ", which blocked the hit completely\n"
                : " and loses " + (hit - resistance) + " hp\n");
    }

    /**
     * Creates message if a fighter dies
     *
     * @param defender the defender
     */
    public static String deadMessage(Fighter defender) {
        return BIG_TAB + "FIGHT IS OVER! " + defender + " has no health points left\n";
    }

    /**
     * Creates message of the fighters current health status as a summary of a battle
     *
     * @param f1 fighter 1
     * @param f2 fighter 2
     */
    public static String battleRoundSummary(Fighter f1, Fighter f2) {
        return BIG_TAB + LINE + "Health Status" + LINE + "\n\n" +
                BIG_TAB + f1 + "\t" + f1.getHealth().createBar() + "\n\n" +
                BIG_TAB + f2 + "\t" + f2.getHealth().createBar() + "\n\n" +
                BIG_TAB + LINE + LINE + LINE + "\n";
    }

    /**
     * Creates message about extra round, when match ends in a tie
     */
    public static String extraFightRound() {
        return BIG_TAB + "All fight rounds are completed, but the fighters health status are even. " +
                "Be prepared for an extra round!\n";
    }

    /**
     * Creates message of winner of the match
     *
     * @param loser  the loser of the match
     * @param winner the winner of the match
     */
    public static String matchWinner(Fighter loser, Fighter winner) {
        return BIG_TAB + loser + " is out!\n" + BIG_TAB + winner + ": " + winner.getMotto() + "\n";
    }

    /**
     * Creates message of the winner of the tournament
     *
     * @param winner the winner of the tournament
     */
    public static String tournamentWinner(Fighter winner) {
        return BIG_TAB + "THE WINNER IS " + winner + "\n" +
                BIG_TAB + winner.getMotto() + "\n";
    }

    /**
     * Creates the profile of a fighter
     *
     * @param f the fighter
     */
    public static String fighterProfile(Fighter f) {
        String message = LINE + LINE + "\n" +
                f + "\n" +
                f.getMotto() + "\n" +
                f.getStrength().createBar() + "\n" +
                f.getResPow().createBar() + "\n" +
                f.getSpeed().createBar() + "\n" +
                "Antal vinster: " + f.getWins() + "\n" +
                LINE + LINE + "\n";
        return message;
    }

    /**
     * Creates a String of several space-constants in a row
     *
     * @param nrOfSpace the number of space-constants to be concatenated
     * @return totalSpace the concatenated String of space
     */
    private static String space(int nrOfSpace) {
        String totalSpace = "";
        for (int i = 0; i < nrOfSpace; i++) {
            totalSpace += SPACE;
        }
        return totalSpace;
    }

    /**
     * Creates a cell in the tournament tree,
     * where the width of the cell is constant independent of the length of the current fighter name
     *
     * @param f the fighter
     * @return cell the String to represent a fighters name
     */
    private static String cell(Fighter f) {
        int nameLength = (f == null ? 0 : f.getName().length());
        String cell = "|" + (f == null ? "" : f);
        for (int i = 0; i < WIDTH - nameLength; i++) {
            cell += " ";
        }
        cell += "|";
        return cell;
    }

    /**
     * Creates String of the tournament tree
     * @param f array of all fighters who participates in the tournament
     * @return tournamentTree the String of the tournament tree
     */
    public static String tree(Fighter[] f) {
        String message = "";
        if(f.length == 31){
            message += FRAME + MessageBuilder.space(11) + FRAME + "\n" +
                    MessageBuilder.cell(f[0]) + MessageBuilder.space(11) + MessageBuilder.cell(f[8]) + "\n" +
                    FRAME + MINUS + MessageBuilder.space(9) + MINUS + FRAME + "\n" +
                    MessageBuilder.cell(f[1]) + R_WALL + MessageBuilder.space(9) + L_WALL + MessageBuilder.cell(f[9]) + "\n" +
                    FRAME + R_WALL + MessageBuilder.space(9) + L_WALL + FRAME + "\n" +

                    MessageBuilder.space(2) + FRAME + MessageBuilder.space(7) + FRAME + "\n" +
                    MessageBuilder.space(2) + MessageBuilder.cell(f[16]) + MessageBuilder.space(7) + MessageBuilder.cell(f[20]) + "\n" +
                    MessageBuilder.space(2) + FRAME + MINUS + MessageBuilder.space(5) + MINUS + FRAME + "\n" +
                    MessageBuilder.space(2) + MessageBuilder.cell(f[17]) + R_WALL + MessageBuilder.space(5) + L_WALL + MessageBuilder.cell(f[21]) + "\n" +
                    MessageBuilder.space(2) + FRAME + R_WALL + MessageBuilder.space(5) + L_WALL + FRAME + "\n" +

                    FRAME + R_WALL + S_R_WALL + MessageBuilder.space(5) + S_L_WALL + L_WALL + FRAME + "\n" +
                    MessageBuilder.cell(f[2]) + R_WALL + S_R_WALL + MessageBuilder.space(2) + STAR_FRAME + MessageBuilder.space(2) + S_L_WALL + L_WALL + MessageBuilder.cell(f[10]) + "\n" +
                    FRAME + MINUS + S_R_WALL + MessageBuilder.space(2) + MessageBuilder.cell(f[30]) + MessageBuilder.space(2) + S_L_WALL + MINUS + FRAME + "\n" +
                    MessageBuilder.cell(f[3]) + MessageBuilder.space(2) + R_WALL + MessageBuilder.space(2) + STAR_FRAME + MessageBuilder.space(2) + L_WALL + MessageBuilder.space(2) + MessageBuilder.cell(f[11]) + "\n" +
                    FRAME + MessageBuilder.space(2) + R_WALL + MessageBuilder.space(2) + WINNER_LINE + MessageBuilder.space(2) + L_WALL + MessageBuilder.space(2) + FRAME + "\n" +

                    MessageBuilder.space(4) + FRAME + MessageBuilder.space(1) + FRAME + MessageBuilder.space(1) + FRAME + "\n" +
                    MessageBuilder.space(4) + MessageBuilder.cell(f[24]) + MessageBuilder.space(1) + MessageBuilder.cell(f[28]) + MessageBuilder.space(1) + MessageBuilder.cell(f[26]) + "\n" +
                    MessageBuilder.space(4) + FRAME + MINUS + FRAME + MINUS + FRAME + "\n" +
                    MessageBuilder.space(4) + MessageBuilder.cell(f[25]) + MessageBuilder.space(1) + MessageBuilder.cell(f[29]) + MessageBuilder.space(1) + MessageBuilder.cell(f[27]) + "\n" +
                    MessageBuilder.space(4) + FRAME + MessageBuilder.space(1) + FRAME + MessageBuilder.space(1) + FRAME + "\n" +

                    FRAME + MessageBuilder.space(2) + R_WALL + MessageBuilder.space(5) + L_WALL + MessageBuilder.space(2) + FRAME + "\n" +
                    MessageBuilder.cell(f[4]) + MessageBuilder.space(2) + R_WALL + MessageBuilder.space(5) + L_WALL + MessageBuilder.space(2) + MessageBuilder.cell(f[12]) + "\n" +
                    FRAME + MINUS + S_R_WALL + MessageBuilder.space(5) + S_L_WALL + MINUS + FRAME + "\n" +
                    MessageBuilder.cell(f[5]) + R_WALL + S_R_WALL + MessageBuilder.space(5) + S_L_WALL + L_WALL + MessageBuilder.cell(f[13]) + "\n" +
                    FRAME + R_WALL + S_R_WALL + MessageBuilder.space(5) + S_L_WALL + L_WALL + FRAME + "\n" +

                    MessageBuilder.space(2) + FRAME + R_WALL + MessageBuilder.space(5) + L_WALL + FRAME + "\n" +
                    MessageBuilder.space(2) + MessageBuilder.cell(f[18]) + R_WALL + MessageBuilder.space(5) + L_WALL + MessageBuilder.cell(f[22]) + "\n" +
                    MessageBuilder.space(2) + FRAME + MINUS + MessageBuilder.space(5) + MINUS + FRAME + "\n" +
                    MessageBuilder.space(2) + MessageBuilder.cell(f[19]) + MessageBuilder.space(7) + MessageBuilder.cell(f[23]) + "\n" +
                    MessageBuilder.space(2) + FRAME + MessageBuilder.space(7) + FRAME + "\n" +

                    FRAME + R_WALL + MessageBuilder.space(9) + L_WALL + FRAME + "\n" +
                    MessageBuilder.cell(f[6]) + R_WALL + MessageBuilder.space(9) + L_WALL + MessageBuilder.cell(f[14]) + "\n" +
                    FRAME + MINUS + MessageBuilder.space(9) + MINUS + FRAME + "\n" +
                    MessageBuilder.cell(f[7]) + MessageBuilder.space(11) + MessageBuilder.cell(f[15]) + "\n" +
                    FRAME + MessageBuilder.space(11) + FRAME;
        }else{
            message = "Not able to create Tournament tree";
        }
        return message;
    }
}

