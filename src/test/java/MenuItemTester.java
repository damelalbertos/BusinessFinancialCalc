import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuItemTester {

    @Test
    public void checkIngredientsExistTest() throws ItemAlreadyExistsException {
        //set up a business with inventory
        BusinessTracker bus1 = new BusinessTracker("Business 1");
        Inventory testInventory = new Inventory();

        //create items (ingredients)
        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Meat", 2.69);
        Item testItem4 = new Item("0004", 5, "Potatoes", 1.50);
        Item testItem5 = new Item("0005", 5, "Cheese", .50);

        //add items (ingredients) to business's inventory
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        //create array list of burger ingredients
        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);

        //create menuItem object (burger)
        MenuItem burger = new MenuItem("001", "Burger", 5.00);
        burger.setItemIngredients(burgerIngredients);

        //create array list of fries ingredients
        ArrayList<Item> friesIngredients = new ArrayList<>();
        friesIngredients.add(testItem4);

        //create second menuItem object (fries)
        MenuItem fries = new MenuItem("002", "Fries", 3.00);
        fries.setItemIngredients(friesIngredients);

        //create array list of cheeseburger ingredients, cheese not in inventory (item 5)
        ArrayList<Item> cheeseburgerIngredients = new ArrayList<>();
        cheeseburgerIngredients.add(testItem1);
        cheeseburgerIngredients.add(testItem2);
        cheeseburgerIngredients.add(testItem3);
        cheeseburgerIngredients.add(testItem5);

        //create third menuItem object (cheeseburger)
        MenuItem cheeseburger = new MenuItem("003", "Cheeseburger", 6.00);
        cheeseburger.setItemIngredients(cheeseburgerIngredients);

        //create array list of cheesyfries ingredients, cheese not in inventory (item 5)
        ArrayList<Item> cheesyfriesIngredients = new ArrayList<>();
        cheesyfriesIngredients.add(testItem4);
        cheesyfriesIngredients.add(testItem5);

        //create fourth menuItem object (cheesyfries)
        MenuItem cheesyfries = new MenuItem("004", "Fries", 3.50);
        cheesyfries.setItemIngredients(cheesyfriesIngredients);



        //Check that passes correctly
        assertTrue(burger.checkIngredientsExist(testInventory.getInventory()));
        assertTrue(fries.checkIngredientsExist(testInventory.getInventory()));

        //Check that fails when not all ingredients are in inventory
        assertFalse(cheeseburger.checkIngredientsExist(testInventory.getInventory()));
        assertFalse(cheesyfries.checkIngredientsExist(testInventory.getInventory()));






    }
}

