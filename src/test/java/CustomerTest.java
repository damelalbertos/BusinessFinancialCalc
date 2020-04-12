import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

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

        //create menuItem object (burger and fries)
        MenuItem burger = new MenuItem("001", "Burger", 5.00);
        MenuItem fries = new MenuItem("002", "Fries", 3.00);
        burger.setItemIngredients(burgerIngredients);
        fries.setItemIngredients(friesIngredients);


        Customer customer1 = new Customer("Bob", "Bobby");

        Customer customer2 = new Customer("Bill", "Billy");

        Customer customer3 = new Customer("Jim", "Jimmy");

        Customer customer4 = new Customer("Sarah", "Smith");

        //Order items
        ArrayList<MenuItem> customer1Order = new ArrayList<>();
        customer1Order.add(burger);
        customer1Order.add(fries);

        //customer 1 orders
        customer1.order(customer1Order, "0", "0");


        //check that items are getting decremented from inventory
        assertEquals(14, testItem1.getCount());
        assertEquals(14, testItem2.getCount());
        assertEquals(14, testItem3.getCount());
        assertEquals(14, testItem4.getCount());

        //customer 2 order items
        ArrayList<MenuItem> customer2Order = new ArrayList<>();
        customer2Order.add(burger);
        customer2Order.add(burger);
        customer2Order.add(burger);

        //customer 2 orders
        customer2.order(customer2Order, "1", "1");

        //customer 3 order items
        ArrayList<MenuItem> customer3Order = new ArrayList<>();
        customer3Order.add(fries);

        //customer 3 order
        customer3.order(customer3Order, "2", "2");

        //customer 4 order items
        ArrayList<MenuItem> customer4Order = new ArrayList<>();
        customer4Order.add(burger);
        customer4Order.add(burger);
        customer4Order.add(fries);
        customer4Order.add(fries);

        //customer 4 order
        customer4.order(customer4Order, "3", "3");

        //check that items are getting decremented from inventory
        assertEquals(9, testItem1.getCount());
        assertEquals(9, testItem2.getCount());
        assertEquals(9, testItem3.getCount());
        assertEquals(11, testItem4.getCount());

        //check order ID's
        assertEquals("0" , bus1.allOrders.get("0").getOrderID());
        assertEquals("1" ,bus1.allOrders.get("1").getOrderID());
        assertEquals("2" , bus1.allOrders.get("2").getOrderID());
        assertEquals("3" , bus1.allOrders.get("3").getOrderID());

        //check items are correct
        assertEquals("Burger, Fries" , bus1.allOrders.get("0").getItemNames());
        assertEquals("Burger, Burger, Burger" ,  bus1.allOrders.get("1").getItemNames());
        assertEquals("Fries" ,  bus1.allOrders.get("2").getItemNames());
        assertEquals("Burger, Burger, Fries, Fries" ,  bus1.allOrders.get("3").getItemNames());

        //check totals are correct
        assertEquals(8, bus1.allOrders.get("0").getTotal());
        assertEquals(15 , bus1.allOrders.get("1").getTotal());
        assertEquals(3, bus1.allOrders.get("2").getTotal());
        assertEquals(16, bus1.allOrders.get("3").getTotal());
    }
}
