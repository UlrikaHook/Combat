package attributes;

/**
 * Sub class of Attributes
 * @author hooku
 */
public class Health extends Attributes {

    /**
     * Constructor
     * Automatically sets power and maxPower to 100, name to enum HEALTH
     * name and maxPower is final
     */
    public Health() {
        super(100, Stats.HEALTH, 100);
    }

    /**
     * Calls the setter of power to reset health to max
     */
    public void resetHealth(){
        setPower(getMaxPower());
    }
}
