import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class JsonTest {
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
        Assert.assertEquals(298, testInventory.getItemCount("003"));


    }


    @Test
    public void testInventoryItems() throws IOException, ItemDoesNotExistsException, ItemAlreadyExistsException {

        CentralBusiness bus1 = new CentralBusiness("Business 1");
        Inventory testInventory = JsonUtil.fromJsonFile("src/test/inventoryTest.json", Inventory.class);


        assertEquals(10, testInventory.getInventoryCount());

        testInventory.removeItem("001");


        assertEquals(9, testInventory.getInventoryCount());

        assertEquals(100, testInventory.getItemCount("002"));

       assertEquals(300, testInventory.getItemCount("004"));

        assertEquals(500, testInventory.getItemCount("003"));


        assertEquals(.50, testInventory.getItemCost("003"));



    }


    @Test
    public void testMenuItems() throws IOException, ItemAlreadyExistsException {

        CentralBusiness bus1 = new CentralBusiness("Business 1");
        Inventory testInventory = new Inventory();

        List<Item> items =  JsonUtil.listFromJsonFile("src/test/setItems.json", Item.class);


        List<MenuItem>menuItems = JsonUtil.listFromJsonFile("src/test/setMenu.json", MenuItem.class);







        for (Item item : items) {
            testInventory.addItem(item);
        }


        assertEquals(11, testInventory.getInventoryCount());



















    }

}
