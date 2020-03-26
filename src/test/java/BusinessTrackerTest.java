import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTrackerTest {

    @Test
    void createMenuTest() {

        BusinessTracker bus1 = new BusinessTracker("Business 1");

        Item item1 = new Item("1", 10, "item1", 10.00);
        Item item2 = new Item("2", 10, "item2", 5.00);
        Item item3 = new Item("3", 10, "item3", 2.25);

        HashMap<String, Item> menu = new HashMap<>();
        menu.put(item1.getItemID(), item1);
        menu.put(item2.getItemID(), item2);
        menu.put(item3.getItemID(), item3);

        bus1.createMenu(menu);

        //Check that the ID's exist in the menu
        assertTrue(bus1.getMenu().containsKey("1"));
        assertTrue(bus1.getMenu().containsKey("2"));
        assertTrue(bus1.getMenu().containsKey("3"));
        assertFalse(bus1.getMenu().containsKey("5"));

        //Check that the menu prices are correct
        assertEquals(bus1.getMenu().get("1").getMenuPrice(), 10.00);
        assertEquals(bus1.getMenu().get("2").getMenuPrice(), 5.00);
        assertEquals(bus1.getMenu().get("3").getMenuPrice(), 2.25);

        //Check that the item names are correct
        assertEquals(bus1.getMenu().get("1").getName(), "item1");
        assertEquals(bus1.getMenu().get("2").getName(), "item2");
        assertEquals(bus1.getMenu().get("3").getName(), "item3");






    }
}
