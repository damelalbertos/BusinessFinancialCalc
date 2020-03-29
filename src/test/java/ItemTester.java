import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTester {

    @Test
    public void isAmountValidTest(){

        //Negative number
        assertFalse(Item.isAmountValid(-5.27));
        assertFalse(Item.isAmountValid(-50.6));
        assertFalse(Item.isAmountValid(-100));
        assertFalse(Item.isAmountValid(-1672.976));

        //Positive Number three decimal places
        assertFalse(Item.isAmountValid(7.505));
        assertFalse(Item.isAmountValid(35.825));
        assertFalse(Item.isAmountValid(206.537));
        assertFalse(Item.isAmountValid(1598.001));

        //Positive number two decimal places (Test that program also recognizes correct user input
        assertTrue(Item.isAmountValid(4.50));
        assertTrue(Item.isAmountValid(97.38));
        assertTrue(Item.isAmountValid(427.94));
        assertTrue(Item.isAmountValid(2374.62));
    }

}


