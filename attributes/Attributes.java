package attributes;

/**
 * Super class of all attributes of a fighter
 * @author hooku
 */
public abstract class Attributes {
    private final Stats name;
    private final double maxPower;
    private double power;

    /**
     * Constructor
     * @param power the specified power
     * @param name the name of the power, enum
     * @param maxPower the maximum power a fighter can have
     */
    public Attributes(double power, Stats name, double maxPower) {
        this.power = power;
        this.name = name;
        this.maxPower = maxPower;
    }

    /**
     * Getter for power
     * @return power
     */
    public double getPower() { return power; }

    /**
     * Setter for power
     * @param power
     */
    public void setPower(double power) { this.power = power; }

    /**
     * Getter for maxPower
     * @return maxPower
     */
    public double getMaxPower() { return maxPower; }

    /**
     * Creates a String to visualize the percentage of the power
     * @return
     */
     public final String createBar() {
        String bar = "[";
        int total = 10;
        int count = Math.round((float)(power/ this.maxPower) * total);
        if(count == 0 && power > 0){ count = 1;}
        for(int i = 0; i < count; i++){
            bar += "#";
        }
        for(int i = 0; i < total - count; i++){
            bar+= " ";
        }
        bar += "]";
        return  bar + " " + this.name;
    }
}
