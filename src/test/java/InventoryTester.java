import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTester {

    @Test
    public void InventoryConstructorTest(){
        Inventory testInventory = new Inventory();

        assertEquals(new HashMap<String, Item>(), testInventory.getInventory());
    }

    @Test
    public void addItemTest(){
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 5, "Burger Patty", 4.00);
        Item testItem5 = new Item("0004", 5, "Mustard", 4.00);
        Item testItem6 = new Item("0006", 5, "Ketchup", -.01);
        Item testItem7 = new Item("0007", 5, "Mushrooms", -50.25);
        Item testItem8 = new Item("0008", 5, "Pickles", 25.312);
        Item testItem9 = new Item("0009", 5, "Mayonnaise", .566970);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);
        assertEquals(testInventory.getInventory().get("0001"), testItem1);
        assertEquals(testInventory.getInventory().get("0002"), testItem2);

        //ID already exists
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem5));

        //Invalid costs
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem6));
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem7));
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem8));
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem9));


    }

    @Test
    public void removeItemTest(){
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);

        testInventory.removeItem("0001");
        testInventory.removeItem("0002");
        assertEquals(null, testInventory.getItem("0001"));
        assertEquals(null, testInventory.getItem("0002"));

        //item does not exist
        assertThrows(IllegalArgumentException.class, () -> testInventory.removeItem("003"));
    }

    @Test
    public void getItemCostTest(){
        Inventory testInventory = new Inventory();
        Item testItem1 = new Item("0001", 5, "Tomatoes", 0.69);
        Item testItem2 = new Item("0002", 5, "Burger Patty", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);

        assertEquals(testInventory.getInventory().get("0001").getCost(), testInventory.getItemCost("0001"));
        assertEquals(testInventory.getInventory().get("0002").getCost(), testInventory.getItemCost("0002"));

        //item does not exist
        assertThrows(IllegalArgumentException.class, () -> testInventory.getItemCost("003"));
    }
}
