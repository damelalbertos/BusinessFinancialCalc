import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

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

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        assertEquals(testInventory.getInventory().get("0001"), testItem1);
        assertEquals(testInventory.getInventory().get("0002"), testItem2);
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
    }
}
