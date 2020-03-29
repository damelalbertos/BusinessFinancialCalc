import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuItemTester {

    @Test
    public void checkIngredientsExistTest(){
        BusinessTracker bus1 = new BusinessTracker("Business 1");
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Meat", 2.69);
        Item testItem4 = new Item("0004", 5, "Potatoes", 1.50);
        Item testItem5 = new Item("0005", 5, "Cheese", .50);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);

        MenuItem burger = new MenuItem("001", "Burger", 5.00, burgerIngredients);

        ArrayList<Item> friesIngredients = new ArrayList<>();
        friesIngredients.add(testItem4);

        MenuItem fries = new MenuItem("002", "Fries", 3.00, friesIngredients);

        ArrayList<Item> cheeseburgerIngredients = new ArrayList<>();
        cheeseburgerIngredients.add(testItem1);
        cheeseburgerIngredients.add(testItem2);
        cheeseburgerIngredients.add(testItem3);
        cheeseburgerIngredients.add(testItem5);

        MenuItem cheeseburger = new MenuItem("003", "Cheeseburger", 6.00, cheeseburgerIngredients);

        ArrayList<Item> cheesyfriesIngredients = new ArrayList<>();
        cheesyfriesIngredients.add(testItem4);
        cheesyfriesIngredients.add(testItem5);

        MenuItem cheesyfries = new MenuItem("004", "Fries", 3.50, cheesyfriesIngredients);



        //Check that passes correctly
        assertTrue(burger.checkIngredientsExist());
        assertTrue(fries.checkIngredientsExist());

        //Check that fails when not all ingredients are in inventory
        assertFalse(cheeseburger.checkIngredientsExist());
        assertFalse(cheesyfries.checkIngredientsExist());






    }
}

