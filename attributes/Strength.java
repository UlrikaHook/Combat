package attributes;

/**
 * Sub class of Attributes
 * @author hooku
 */
public class Strength extends Attributes {

    /**
     * Constructor
     * Automatically sets name to enum SPEED and maxPower 0.3, which will be final
     * @param power the specified strength of the fighter that owns the strength object
     */
    public Strength(double power) { super(power, Stats.STRENGTH, 70); }

}
