package attributes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttributesTest {

    private static Attributes attributes;

    @BeforeAll
    static void setUpAll(){
        attributes = new ResistancePower(40);
    }

    @Test
    void createBar_PowerEqualToMaxPower_FullBar() {
        attributes.setPower(attributes.getMaxPower());
        assertEquals("[##########] RESISTANCEPOWER", attributes.createBar());
    }

    @Test
    void createBar_PowerHalfOfMaxPower_HalfBar(){
        attributes.setPower(attributes.getMaxPower()/2);
        assertEquals("[#####     ] RESISTANCEPOWER", attributes.createBar());
    }
}