import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTester {

    @Test
    public void isAmountValidTest(){

        //Negative number
        assertFalse(Item.isAmountValid(-.01));
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

    @Test
    public void ItemConstructorTest(){
        //Invalid Costs
        assertThrows(IllegalArgumentException.class, () -> new Item("0006", 5, "Ketchup", -.01));
        assertThrows(IllegalArgumentException.class, () -> new Item("0007", 5, "Mushrooms", -50.25));
        assertThrows(IllegalArgumentException.class, () -> new Item("0008", 5, "Pickles", 25.312));
        assertThrows(IllegalArgumentException.class, () -> new Item("0009", 5, "Mayonnaise", .566970));

        //Invalid ItemCount
        assertThrows(IllegalArgumentException.class, () -> new Item("0006", -1, "Ketchup", .01));
        assertThrows(IllegalArgumentException.class, () -> new Item("0007", -10, "Mushrooms", 50));
        assertThrows(IllegalArgumentException.class, () -> new Item("0008", -100, "Pickles", 25));
    }
}


