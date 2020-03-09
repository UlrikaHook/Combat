package fighter;

import attributes.Health;
import attributes.ResistancePower;
import attributes.Speed;
import attributes.Strength;

import java.util.Random;

/**
 * Holds fighter objects
 */
public class Fighter {
    private Random rand = new Random();

    private String name;
    private String motto;
    private int wins;

    private Strength strength;
    private ResistancePower resPow;
    private Speed speed;
    private Health health;

    /**
     * Constructor
     * @param name the name of the fighter
     * @param motto the motto of the fighter
     * @param strength the strength of the fighter, sent to Strength constructor
     * @param resistancePower the resistance power of the fighter, sent to ResistancePower constructor
     * @param speed the speed of the fighter, sent to Speed constructor
     * @param wins the number of won tournaments
     */
    public Fighter(String name, String motto, int strength, int resistancePower, double speed, int wins) {
        this.name = name;
        this.motto = motto;
        this.health = new Health();
        this.strength = new Strength(strength);
        this.resPow = new ResistancePower(resistancePower);
        this.speed = new Speed(speed);
        this.wins = wins;
    }

    /**
     * Algorithm to determine the power of the hit in attack
     * @return fighters strength +/- 10%
     */
    public int attack(){
        return (int)(strength.getPower() * ((rand.nextInt(3) + 9)*0.1));
    }

    /**
     * Algorithm to determine the power of the defence when being hit
     * Calculates damage and decreases health by the same number
     * @param hit the power of the attack
     * @return resistance - fighters resistance power +/- 10%
     */
    public int defend(int hit){
        int resistance = (int)(resPow.getPower() * ((rand.nextInt(3) + 9)*0.1));
        int damage = hit - resistance;
        this.health.setPower(damage < 0 ? this.health.getPower() : this.health.getPower() - damage);
        return resistance;
    }

    /**
     * Algorithm to determine whether the fighter escapes the attack or not
     * Speed can be up to 0.3, which means maximum 30 % likelihood of escaping
     * @return true if fighter escaped, false if not
     */
    public boolean escapedHit(){
        return (Math.random() < speed.getPower());
    }

    /**
     * Check to see if fighter is dead
     * @return true if health is zero or less, else false.
     */
    public boolean isDead(){
        return (this.health.getPower() <= 0);
    }

    /**
     * Check to see if the fighter won against the opponent
     * @param opponent the opponent
     * @return true if health of fighter is more than health of opponent, else false.
     */
    public boolean wonAgainst(Fighter opponent){
        return (this.health.getPower() > opponent.health.getPower());
    }

    /**
     * toString
     * @return the name of the fighter
     */
    @Override
    public String toString() {
       return this.name;
    }

    /**
     * Getter for resistance power
     * @return resPow the instance of ResistancePower
     */
    public ResistancePower getResPow() { return resPow; }

    /**
     * Getter for strength
     * @return strength the instance of Strength
     */
    public Strength getStrength() { return strength; }

    /**
     * Getter for speed
     * @return speed the instance of Speed
     */
    public Speed getSpeed() { return speed; }

    /**
     * Getter for health
     * @return health the instance of Health
     */
    public Health getHealth() { return health; }

    /**
     * Getter for wins
     * @return wins the number of won tournaments
     */
    public int getWins() {
        return wins;
    }

    /**
     * Getter for name
     * @return name the name of the fighter
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for motto
     * @return motto the motto of the fighter
     */
    public String getMotto() {
        return motto;
    }

    /**
     * Increases the number of wins by one
     */
    public void addWin() {
        this.wins += 1;
    }

}
