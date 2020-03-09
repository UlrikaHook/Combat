package attributes;

/**
 * Sub class of Attributes
 * @author hooku
 */
public class Speed extends Attributes {

    /**
     * Constructor
     * Automatically sets name to enum SPEED and maxPower 0.3, which will be final
     * @param power the specified speed of the fighter that owns the speed object
     */
    public Speed(double power) {
        super(power, Stats.SPEED, 0.3);
    }
}
