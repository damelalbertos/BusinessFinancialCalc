import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTrackerTest {

    @Test
    void addToMenuTest() {

        BusinessTracker bus1 = new BusinessTracker("Business 1");
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 5, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 5, "Coke", 1.00);
        Item testItem6 = new Item("0006", 5, "Chicken", 4.00);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);
        testInventory.addItem(testItem5);

        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);
        burgerIngredients.add(testItem4);

        ArrayList<Item> cokeIngredients = new ArrayList<>();
        cokeIngredients.add(testItem5);

        MenuItem menuItem1 = new MenuItem("1", "burger", 7.00, burgerIngredients);
        MenuItem menuItem2 = new MenuItem("2", "coke", 1.50, cokeIngredients);

        bus1.addToMenu(menuItem1);
        bus1.addToMenu(menuItem2);

        ArrayList<Item> chickenIngredients = new ArrayList<>();
        chickenIngredients.add(testItem1);

        MenuItem menuItem3 = new MenuItem("3", "chicken", 6.00, chickenIngredients);

        MenuItem menuItem4 = new MenuItem("3", "chicken", 6.220, chickenIngredients);
        MenuItem menuItem5 = new MenuItem("3", "chicken", -6.00, chickenIngredients);

        MenuItem menuItem6 = new MenuItem("1", "chicken", 6.00, chickenIngredients);

        //Check that the ID's exist in the menu
        assertTrue(bus1.getMenu().containsKey("1"));
        assertTrue(bus1.getMenu().containsKey("2"));
        assertFalse(bus1.getMenu().containsKey("5"));

        //Check that the menu prices are correct
        assertEquals(bus1.getMenu().get("1").getPrice(), 7.00);
        assertEquals(bus1.getMenu().get("2").getPrice(), 1.50);
        
        //Check that the item names are correct
        assertEquals(bus1.getMenu().get("1").getMenuItemName(), "burger");
        assertEquals(bus1.getMenu().get("2").getMenuItemName(), "coke");

        //Check for right ingredients
        assertEquals(bus1.getMenu().get("1").getItemIngredients().get(0).getName(), "Buns");
        assertEquals(bus1.getMenu().get("1").getItemIngredients().get(2).getName(), "Tomatoes");
        assertEquals(bus1.getMenu().get("2").getItemIngredients().get(0).getName(), "Coke");

        //Not all ingredients in inventory for menu, should throw exception
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem3));

        //Invalid item cost, should throw exception
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem4));
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem5));

        //Duplicate IDs, should throw exception
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem6));

    }

    @org.junit.Test
    public void  calcPayTest(){

        BusinessTracker business = new BusinessTracker("BusinessName");

        Employees employee1 = new Employees("1000", 11.25, 36);
        Employees employee2 = new Employees("1001", 13.25, 50);
        Employees employee3 = new Employees("1002", 10, 40);
        Employees employee4 = new Employees("1003", 15.37, 27);
        Employees employee5 = new Employees("1004", 14.26, 42);
        business.addAccount("1000", employee1);
        business.addAccount("1001", employee2);
        business.addAccount("1002", employee3);
        business.addAccount("1003", employee4);
        business.addAccount("1004", employee5);


        assertEquals(405, business.calcPay("1000"));
        assertEquals(728.75, business.calcPay("1001"));
        assertEquals(400, business.calcPay("1002"));
        assertEquals(414.99, business.calcPay("1003"));
        assertEquals(613.18, business.calcPay("1004"));
        assertThrows(IllegalArgumentException.class, () -> business.calcPay("134324"));
        assertThrows(IllegalArgumentException.class, () -> business.calcPay("0"));






    }

    @org.junit.Test
    public void calcOvertimePayTest(){

        BusinessTracker business = new BusinessTracker("BusinessName");

        Employees employee = new Employees("100", 14.26, 42);
        Employees employee2 = new Employees("1001", 13.25, 50);

        business.addAccount("100", employee);
        business.addAccount("1001", employee2);

        assertEquals(42.78, business.calcOvertimePay("100", 2));
        assertEquals(198.75, business.calcOvertimePay("1001", 10));

    }

}
