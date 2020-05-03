import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {

    @Test
    public void InventoryConstructorTest(){
        Inventory testInventory = new Inventory();

        //test that the constructor successfully creates the inventory
        assertEquals(new HashMap<String, Item>(), testInventory.getInventory());
    }

    @Test
    public void addItemTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 500, "Buns", 1.00);
        Item testItem2 = new Item("0002", 500, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 500, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 500, "Burger Patty", 4.00);
        Item testItem5 = new Item("0004", 500, "Mustard", 4.00);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        //check if the item was successfully added to the inventory
        assertEquals(testInventory.getInventory().get("0001"), testItem1);
        assertEquals(testInventory.getInventory().get("0002"), testItem2);


        //ID already exists
        assertThrows(ItemAlreadyExistsException.class, () -> testInventory.addItem(testItem5));

        //test when the ID already exists
        assertThrows(ItemAlreadyExistsException.class, () -> testInventory.addItem(testItem5));
    }

    @Test
    public void removeItemTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 500, "Buns", 1.00);
        Item testItem2 = new Item("0002", 500, "Lettuce", 0.50);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);

        testInventory.removeItem("0001");
        testInventory.removeItem("0002");

        //test that the item was successfully removed
        assertNull(testInventory.getItem("0001"));
        assertNull(testInventory.getItem("0002"));

        //item does not exist
        //test when that an item that does not exist can't be removed
        assertThrows(ItemDoesNotExistsException.class, () -> testInventory.removeItem("003"));
    }

    @Test
    public void getItemCostTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        Inventory testInventory = new Inventory();
        Item testItem1 = new Item("0001", 500, "Tomatoes", 0.69);
        Item testItem2 = new Item("0002", 500, "Burger Patty", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);

        //test that method is returning the cost of the specified item
        assertEquals(testInventory.getInventory().get("0001").getCost(), testInventory.getItemCost("0001"));
        assertEquals(testInventory.getInventory().get("0002").getCost(), testInventory.getItemCost("0002"));

        //item does not exist
        assertThrows(ItemDoesNotExistsException.class, () -> testInventory.getItemCost("003"));
    }


    @Test
    public void getProductInventoryTest() throws ItemAlreadyExistsException, EmptyInventoryException {
        Inventory testInventory = new Inventory();
        //test that it throws inventory empty exception
        assertThrows(EmptyInventoryException.class, () -> testInventory.getProductInventory());

        //test with  items
        Item testItem1 = new Item("0001", 200, "Burger Patty", 1.00);
        Item testItem2 = new Item("0002", 150, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 150, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 150, "Buns", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        String expected = "Buns: 150\n";
        expected += "Lettuce: 150\n";
        expected += "Tomatoes: 150\n";
        expected += "Burger Patty: 200\n";

        assertEquals(expected, testInventory.getProductInventory());
    }

    @Test
    public void getItemCountTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException {
        Inventory testInventory = new Inventory();
        //test with  items
        Item testItem1 = new Item("0001", 200, "Burger Patty", 1.00);
        Item testItem2 = new Item("0002", 150, "Lettuce", 0.50);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);


        //test that the method returns the specified item's count
        assertEquals(200, testInventory.getItemCount("0001"));
        assertEquals(150, testInventory.getItemCount("0002"));

        testInventory.removeItem("0001");

        //test that it throws exception if item is not in inventory
        assertThrows(ItemDoesNotExistsException.class, () -> testInventory.getItemCount("0001"));
    }

    @Test
    public void getInventoryCountTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 200, "Burger Patty", 1.00);
        Item testItem2 = new Item("0002", 150, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 150, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 150, "Buns", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        //test that the method returns the amount of items in the inventory
        assertEquals(4, testInventory.getInventoryCount());

        testInventory.removeItem("0001");

        //double check the inventoryCount check
        assertEquals(3, testInventory.getInventoryCount());
    }

    @Test
    public void decrementItemTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 20, "Burger Patty", 1.00);
        Item testItem2 = new Item("0002", 0, "Buns", 0.60);
        Item testItem3 = new Item("0003", 1, "Cheese", 0.40);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);

        testInventory.decrementItem("0001");
        assertEquals(19, testInventory.getItemCount("0001"));

        //test decrement on item that doesn't exist
        assertThrows(ItemDoesNotExistsException.class, () -> testInventory.decrementItem("1234"));

        //test item with itemCount = 0
        assertThrows(ItemCountAt0Exception.class, () -> testInventory.decrementItem("0002"));

        //test item with itemCount = 1, should be 0 after decrement
        testInventory.decrementItem("0003");
        assertEquals(0, testInventory.getItemCount("0003"));
    }

    @Test
    public void decrementItemJsonTest() throws ItemDoesNotExistsException, ItemCountAt0Exception, IOException {
        Inventory testInventory = JsonUtil.fromJsonFile("src/test/inventoryTest.json", Inventory.class);
        testInventory.decrementItem("001");
        Assert.assertEquals(99, testInventory.getItemCount("001"));

        testInventory.decrementItem("002");
        testInventory.decrementItem("002");
        testInventory.decrementItem("002");
        Assert.assertEquals(97, testInventory.getItemCount("002"));

        testInventory.decrementItem("003");
        testInventory.decrementItem("003");
        Assert.assertEquals(498, testInventory.getItemCount("003"));


    }


}
