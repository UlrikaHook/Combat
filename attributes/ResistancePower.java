package attributes;

/**
 * Sub class of Attributes
 * @author hooku
 */
public class ResistancePower extends Attributes {

    /**
     * Constructor
     * Automatically sets the name to enum RESISTANCEPOWER and maxPower to 40, which will be final
     * @param power the specified resistance power of the fighter that owns the ResistancePower object
     */
    public ResistancePower(double power) {
        super(power, Stats.RESISTANCEPOWER, 40);
    }
}
