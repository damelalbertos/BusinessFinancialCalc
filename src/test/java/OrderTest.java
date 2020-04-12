import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    /**
    @Test
    public void orderTest() throws ItemCountAt0Exception, ItemDoesNotExistsException, ItemAlreadyExistsException{
        CentralBusiness bus1 = new CentralBusiness("Business 1");
        Inventory testInventory = new Inventory();



        //create items (ingredients)
        Item testItem1 = new Item("0001", 15, "Buns", 1.00);
        Item testItem2 = new Item("0002", 15, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 15, "Meat", 2.69);
        Item testItem4 = new Item("0004", 15, "Potatoes", 1.50);

        //add items to inventory
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        //add burger ingredients
        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);

        //add fries ingredients
        ArrayList<Item> friesIngredients = new ArrayList<>();
        friesIngredients.add(testItem4);

        //create menuItem object (burger)
        MenuItem burger = new MenuItem("001", "Burger", 5.00);
        MenuItem fries = new MenuItem("002", "Fries", 3.00);
        burger.setItemIngredients(burgerIngredients);
        fries.setItemIngredients(friesIngredients);


        Customer customer1 = new Customer("001", "Bob");

        Customer customer2 = new Customer("004", "Billy");

        Customer customer3 = new Customer("006", "Jim");

        Customer customer4 = new Customer("007", "Game");

        customer1.order(burger);
        customer1.order(fries);

        //check that items are getting decremented from inventory
        assertEquals(14, testItem1.getCount());
        assertEquals(14, testItem2.getCount());
        assertEquals(14, testItem3.getCount());
        assertEquals(14, testItem4.getCount());

        customer2.order(burger);
        customer2.order(burger);
        customer2.order(burger);

        customer3.order(fries);

        customer4.order(burger);
        customer4.order(fries);
        customer4.order(fries);
        customer4.order(burger);

        //check that items are getting decremented from inventory
        assertEquals(9, testItem1.getCount());
        assertEquals(9, testItem2.getCount());
        assertEquals(9, testItem3.getCount());
        assertEquals(11, testItem4.getCount());

        assertEquals("001" , customer1.getOrderId());
        assertEquals("004" , customer2.getOrderId());
        assertEquals("006" , customer3.getOrderId());

        assertEquals("Bob" , customer1.getCustomerName());
        assertEquals("Billy" , customer2.getCustomerName());
        assertEquals("Jim" , customer3.getCustomerName());





        assertEquals("Burger, Fries" , customer1.getItems());
        assertEquals("Burger, Burger, Burger" , customer2.getItems());
        assertEquals("Fries" , customer3.getItems());
        assertEquals("Burger, Fries, Fries, Burger" , customer4.getItems());


        assertEquals(8, customer1.getTotal());
        assertEquals(15 , customer2.getTotal());
        assertEquals(3, customer3.getTotal());
        assertEquals(16, customer4.getTotal());
    }
    **/

    @Test
    public void incrementCustomerIDTest() {
        //test that orderID set to 1 if no numeric string value
        Order order = new Order("1", "", 1.00);
        order.incrementCustomerID();
        assertEquals("0", order.getCustomerID());

        //Check that gets incremented by 1 if between 0-999
        Order order1 = new Order("1", "200", 1.00);
        order1.incrementCustomerID();
        assertEquals("201", order1.getCustomerID());

        //Check that gets reset to 0 if goes over 999
        Order order2 = new Order("1", "999", 1.00);
        order2.incrementCustomerID();
        assertEquals("0", order2.getCustomerID());

    }

    @Test
    public void incrementOrderIDTest() {
        //test that orderID set to 1 if no numeric string value
        Order order = new Order("", "100", 1.00);
        order.incrementOrderID();
        assertEquals("0", order.getOrderID());

        //Check that gets incremented by 1 if between 0-999
        Order order1 = new Order("571", "100", 1.00);
        order1.incrementOrderID();
        assertEquals("572", order1.getOrderID());

        //Check that gets reset to 0 if goes over 999
        Order order2 = new Order("999", "100", 1.00);
        order2.incrementOrderID();
        assertEquals("0", order2.getOrderID());


    }

}
