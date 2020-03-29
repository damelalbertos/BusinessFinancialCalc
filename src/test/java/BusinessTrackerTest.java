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
        assertEquals(bus1.getMenu().get("1").getCost(), 10.00);
        assertEquals(bus1.getMenu().get("2").getCost(), 5.00);
        assertEquals(bus1.getMenu().get("3").getCost(), 2.25);

        //Check that the item names are correct
        assertEquals(bus1.getMenu().get("1").getName(), "item1");
        assertEquals(bus1.getMenu().get("2").getName(), "item2");
        assertEquals(bus1.getMenu().get("3").getName(), "item3");
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
