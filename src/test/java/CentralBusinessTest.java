import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBusinessTest {

    @Test
    public void buyMoreItemsTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        //make Business
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        Item testItem1 = new Item("0001", 500, "Buns", 1.00);
        Item testItem2 = new Item("0002", 500, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 500, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 500, "Burger Patty", 4.00);
        bus1.getInventory().addItem(testItem1);
        bus1.getInventory().addItem(testItem2);
        bus1.getInventory().addItem(testItem3);
        bus1.getInventory().addItem(testItem4);

        bus1.buyMoreProducts("0001", 5);
        bus1.buyMoreProducts("0004", 10);
        bus1.buyMoreProducts("0002", 100);
        bus1.buyMoreProducts("0003", 4);




        //test that the method successfully adds to the inventory count
        assertEquals(505, bus1.getInventory().getItemCount("0001"));
        assertEquals(510, bus1.getInventory().getItemCount("0004"));
        assertEquals(600, bus1.getInventory().getItemCount("0002"));
        assertEquals(504, bus1.getInventory().getItemCount("0003"));

        //test when the specified item does not exist
        assertThrows(ItemDoesNotExistsException.class, () ->bus1.buyMoreProducts("0006", 10));
    }

    @org.junit.Test
    public void addToExpensesTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException {
        //make Business
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //create items
        Item testItem1 = new Item("0001", 500, "Buns", 1.00);
        Item testItem2 = new Item("0002", 500, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 100, "Meat", 2.50);

        //add items to the inventory
        bus1.getInventory().addItem(testItem1);
        bus1.getInventory().addItem(testItem2);
        bus1.getInventory().addItem(testItem3);

        //buyMoreProducts 1
        bus1.buyMoreProducts("0001", 5); //1.00 * 5
        assertEquals(5.0, bus1.getExpenses());

        //buyMoreProducts 2
        //tests that it adds to a previous addition to expenses
        bus1.buyMoreProducts("0002", 7);//0.50 * 7 + 5
        assertEquals(8.5,bus1.getExpenses());

        //create employees
        Employee employee1 = new Employee();
        employee1.setId("1000");
        employee1.setWage(11.25);
        employee1.setHoursWorked(36);
        Employee employee2 = new Employee();
        employee2.setId("1001");
        employee2.setWage(13.25);
        employee2.setHoursWorked(50);
        Employee employee3 = new Employee();
        employee3.setId("1002");
        employee3.setWage(10);
        employee3.setHoursWorked(40);

        //add employees to system
        bus1.addAccount("1000", employee1);
        bus1.addAccount("1001", employee2);
        bus1.addAccount("1002", employee3);

        //calculate pay for first employee
        bus1.calcPay("1000"); //expenses is already $8.50, after calcpay should be 413.50
        assertEquals(413.5, bus1.getExpenses());

        //calculate pay for second employee
        double overtimeEmployeePay = bus1.calcPay("1001");
        assertEquals(413.5+overtimeEmployeePay, bus1.getExpenses());

    }

    @org.junit.Test
    public void addToRevenueTest() throws ItemAlreadyExistsException, ItemCountAt0Exception, ItemDoesNotExistsException{

        //set up a business with an inventory
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //create items (ingredients)
        Item testItem1 = new Item("0001", 250, "Buns", 1.00);
        Item testItem2 = new Item("0002", 250, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 250, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 250, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 250, "Coke", 1.00);
        Item testItem6 = new Item("0006", 250, "Chicken", 4.00);

        //add items to business's inventory system
        bus1.getInventory().addItem(testItem1);
        bus1.getInventory().addItem(testItem2);
        bus1.getInventory().addItem(testItem3);
        bus1.getInventory().addItem(testItem4);
        bus1.getInventory().addItem(testItem5);

        //create an array list of burger ingredients
        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);
        burgerIngredients.add(testItem4);

        //create an array list of coke ingredients
        ArrayList<Item> cokeIngredients = new ArrayList<>();
        cokeIngredients.add(testItem5);

        //create the first menuItem object (burger) and set its ingredients
        MenuItem menuItem1 = new MenuItem("1", "burger", 7);
        menuItem1.setItemIngredients(burgerIngredients);

        //create the second menuItem object (coke) and set its ingredients
        MenuItem menuItem2 = new MenuItem("2", "coke", 1.50);
        menuItem2.setItemIngredients(cokeIngredients);

        //add the two menuItems to the business's menu

        bus1.addToMenu(menuItem1, bus1.getInventory().getInventory());
        bus1.addToMenu(menuItem2, bus1.getInventory().getInventory());

        //create 4 customers for ordering
        Customer customer1 = new Customer("Bobby", "Billy", "0");

        Customer customer2 = new Customer("Beth", "Jackson", "1");

        Customer customer3 = new Customer("Jimmy", "Jim", "2");

        Customer customer4 = new Customer("Tim", "Smith", "3");

        //Order items
        ArrayList<MenuItem> customer1Order = new ArrayList<>();
        customer1Order.add(menuItem1);

        //customer 1 orders
        bus1.order(customer1Order, customer1, "0");

        //check that revenue gets summed correctly
        assertEquals(7, bus1.getRevenue());

        //another orders occur
        ArrayList<MenuItem> customer2Order = new ArrayList<>();
        customer2Order.add(menuItem2);
        bus1.order(customer2Order, customer2, "1");

        //check that revenue gets summed correctly
        assertEquals(8.50, bus1.getRevenue());

        //another orders occur
        ArrayList<MenuItem> customer3Order = new ArrayList<>();
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem2);
        bus1.order(customer3Order, customer3, "2");

        //check that revenue gets summed correctly
        assertEquals(31, bus1.getRevenue());

        //more orders occur
        ArrayList<MenuItem> customer4Order = new ArrayList<>();
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem2);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem1);
        bus1.order(customer4Order, customer4, "3");

        //check that revenue gets summed correctly
        assertEquals(67.50, bus1.getRevenue());
        assertNotEquals(15,bus1.getRevenue());
    }


    @org.junit.Test
    public void addToMenuTest() throws ItemAlreadyExistsException {

        //set up a business with an inventory
        CentralBusiness bus1 = new CentralBusiness("Business 1");
        Inventory testInventory = new Inventory();

        //create items (ingredients)
        Item testItem1 = new Item("0001", 500, "Buns", 1.00);
        Item testItem2 = new Item("0002", 500, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 500, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 500, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 500, "Coke", 1.00);
        Item testItem6 = new Item("0006", 500, "Chicken", 4.00);

        //add items to business's inventory system
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);
        testInventory.addItem(testItem5);

        //create an array list of burger ingredients
        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);
        burgerIngredients.add(testItem4);

        //create an array list of coke ingredients
        ArrayList<Item> cokeIngredients = new ArrayList<>();
        cokeIngredients.add(testItem5);

        //create the first menuItem object (burger) and set its ingredients
        MenuItem menuItem1 = new MenuItem("1", "burger", 7.00);
        menuItem1.setItemIngredients(burgerIngredients);

        //create the second menuItem object (coke) and set its ingredients
        MenuItem menuItem2 = new MenuItem("2", "coke", 1.50);
        menuItem2.setItemIngredients(cokeIngredients);

        //add the two menuItems to the business's menu
        bus1.addToMenu(menuItem1, testInventory.getInventory());
        bus1.addToMenu(menuItem2, testInventory.getInventory());

        //create an array list of chicken ingredients
        ArrayList<Item> chickenIngredients = new ArrayList<>();
        chickenIngredients.add(testItem6);

        //create the third menuItem object (chicken) and set its ingredients. ingredients not in inventory
        MenuItem menuItem3 = new MenuItem("3", "chicken", 6.00);
        menuItem3.setItemIngredients(chickenIngredients);

        //create more menuItem objects with invalid amounts
        MenuItem menuItem4 = new MenuItem("3", "chicken", 6.220);
        menuItem4.setItemIngredients(chickenIngredients);
        MenuItem menuItem5 = new MenuItem("3", "chicken", -6.00);
        menuItem5.setItemIngredients(chickenIngredients);

        //create another menuItem object with an ID that already exists
        MenuItem menuItem6 = new MenuItem("1", "chicken", 6.00);
        menuItem6.setItemIngredients(chickenIngredients);

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
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem3, testInventory.getInventory()));

        //Invalid item cost, should throw exception
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem4, testInventory.getInventory()));
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem5, testInventory.getInventory()));

        //Duplicate IDs, should throw exception
        assertThrows(IllegalArgumentException.class, () -> bus1.addToMenu(menuItem6, testInventory.getInventory()));

    }

    @org.junit.Test
    public void  calcPayTest(){

        CentralBusiness business = new CentralBusiness("BusinessName");


        Employee employee1 = new Employee();
        employee1.setId("1000");
        employee1.setWage(11.25);
        employee1.setHoursWorked(36);
        Employee employee2 = new Employee();
        employee2.setId("1001");
        employee2.setWage(13.25);
        employee2.setHoursWorked(50);
        Employee employee3 = new Employee();
        employee3.setId("1002");
        employee3.setWage(10);
        employee3.setHoursWorked(40);
        Employee employee4 = new Employee();
        employee4.setId("1003");
        employee4.setWage(15.37);
        employee4.setHoursWorked(27);
        Employee employee5 = new Employee();
        employee5.setId("1004");
        employee5.setWage(14.26);
        employee5.setHoursWorked(42);

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

        CentralBusiness business = new CentralBusiness("BusinessName");

        Employee employee = new Employee();
        employee.setId("100");
        employee.setWage(14.26);
        employee.setHoursWorked(42);
        Employee employee2 = new Employee();
        employee2.setId("1001");
        employee2.setWage(13.25);
        employee2.setHoursWorked(50);

        business.addAccount("100", employee);
        business.addAccount("1001", employee2);

        assertEquals(42.78, business.calcOvertimePay("100", 2));
        assertEquals(198.75, business.calcOvertimePay("1001", 10));

    }

    @Test
    public void getRevenueByItemTest() throws ItemAlreadyExistsException, ItemCountAt0Exception, ItemDoesNotExistsException{
        //set up a business with an inventory
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //create items (ingredients)
        Item testItem1 = new Item("0001", 250, "Buns", 1.00);
        Item testItem2 = new Item("0002", 250, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 250, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 250, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 250, "Coke", 1.00);
        Item testItem6 = new Item("0006", 250, "Chicken", 4.00);

        //add items to business's inventory system
        bus1.getInventory().addItem(testItem1);
        bus1.getInventory().addItem(testItem2);
        bus1.getInventory().addItem(testItem3);
        bus1.getInventory().addItem(testItem4);
        bus1.getInventory().addItem(testItem5);

        //create an array list of burger ingredients
        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);
        burgerIngredients.add(testItem4);

        //create an array list of coke ingredients
        ArrayList<Item> cokeIngredients = new ArrayList<>();
        cokeIngredients.add(testItem5);

        //create the first menuItem object (burger) and set its ingredients
        MenuItem menuItem1 = new MenuItem("1", "burger", 8.50);
        menuItem1.setItemIngredients(burgerIngredients);

        //create the second menuItem object (coke) and set its ingredients
        MenuItem menuItem2 = new MenuItem("2", "coke", 1.75);
        menuItem2.setItemIngredients(cokeIngredients);

        //not added to menu
        MenuItem menuItem3 = new MenuItem("3", "hotdog", 5.75);
        menuItem3.setItemIngredients(burgerIngredients);



        //add the two menuItems to the business's menu
        bus1.addToMenu(menuItem1, bus1.getInventory().getInventory());
        bus1.addToMenu(menuItem2, bus1.getInventory().getInventory());

        //create 4 customers for ordering
        Customer customer1 = new Customer("Nate", "Jackson", "0");

        Customer customer2 = new Customer("Hannah", "Smith", "1");

        Customer customer3 = new Customer("James", "Johnson", "2");

        Customer customer4 = new Customer("Makenzie", "Lee", "3");

        //Order items
        ArrayList<MenuItem> customer1Order = new ArrayList<>();
        customer1Order.add(menuItem1);

        //customer 1 orders
        bus1.order(customer1Order, customer1, "0");

        //check that revenue gets summed correctly for each item
        assertEquals(8.50, bus1.getRevenueByItem(menuItem1));
        assertEquals(0, bus1.getRevenueByItem(menuItem2));


        //another orders occur
        ArrayList<MenuItem> customer2Order = new ArrayList<>();
        customer2Order.add(menuItem2);
        bus1.order(customer2Order, customer2, "1");

        //check that revenue gets summed correctly
        assertEquals(8.50, bus1.getRevenueByItem(menuItem1));
        assertEquals(1.75, bus1.getRevenueByItem(menuItem2));

        //another orders occur
        ArrayList<MenuItem> customer3Order = new ArrayList<>();
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem2);
       bus1.order(customer3Order, customer3, "2");

        //check that revenue gets summed correctly
        assertEquals(34, bus1.getRevenueByItem(menuItem1));
        assertEquals(3.50, bus1.getRevenueByItem(menuItem2));

        //more orders occur
        ArrayList<MenuItem> customer4Order = new ArrayList<>();
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem2);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem1);
        customer4Order.add(menuItem2);
        customer4Order.add(menuItem2);
        bus1.order(customer4Order, customer4, "3");

        //check that revenue gets summed correctly
        assertEquals(76.50, bus1.getRevenueByItem(menuItem1));
        assertEquals(8.75, bus1.getRevenueByItem(menuItem2));

        //check exception thrown when menu item not in menu
        assertThrows(ItemDoesNotExistsException.class, ()-> bus1.getRevenueByItem(menuItem3));
    }

    @org.junit.jupiter.api.Test
    public void orderTest() throws ItemCountAt0Exception, ItemDoesNotExistsException, ItemAlreadyExistsException{
        CentralBusiness bus1 = new CentralBusiness("Business 1");
        bus1.setInventoryThreshold(150);
        bus1.setReorderAmount(100);



        //create items (ingredients)
        Item testItem1 = new Item("0001", 149, "Buns", 1.00);
        Item testItem2 = new Item("0002", 149, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 149, "Meat", 2.69);
        Item testItem4 = new Item("0004", 149, "Potatoes", 1.50);
        Item testItem5 = new Item("0005", 0, "bacon", 2.20);

        //add items to inventory
        bus1.getInventory().addItem(testItem1);
        bus1.getInventory().addItem(testItem2);
        bus1.getInventory().addItem(testItem3);
        bus1.getInventory().addItem(testItem4);
        bus1.getInventory().addItem(testItem5);

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

        //menuItem to test count < 1 ordering
        MenuItem bacon = new MenuItem("003", "Bacon", 3.00);
        ArrayList<Item> baconIngredients = new ArrayList<>();
        baconIngredients.add(testItem5);
        bacon.setItemIngredients(baconIngredients);

        //menuItem to test items not on business menu (never gets added to menu)
        MenuItem burger2 = new MenuItem("004", "burger2", 6.00);


        bus1.addToMenu(burger, bus1.getInventory().getInventory());
        bus1.addToMenu(fries, bus1.getInventory().getInventory());
        bus1.addToMenu(bacon, bus1.getInventory().getInventory());

        Customer customer1 = new Customer("Bob", "Bobby", "0");

        Customer customer2 = new Customer("Bill", "Billy", "1");

        Customer customer3 = new Customer("Jim", "Jimmy", "2");

        Customer customer4 = new Customer("Sarah", "Smith", "3");

        //Order items
        ArrayList<MenuItem> customer1Order = new ArrayList<>();
        customer1Order.add(burger);
        customer1Order.add(fries);

        double curExp = bus1.getExpenses();

        //customer 1 orders
        bus1.order(customer1Order, customer1, "0");

        //check that items are getting decremented from inventory, and got automatically re-ordered when went under threshold (150) for a new order
        assertEquals(248, testItem1.getCount());
        assertEquals(248, testItem2.getCount());
        assertEquals(248, testItem3.getCount());
        assertEquals(248, testItem4.getCount());

        //check expenses get added for automatic product purchase
        //100*1 + 100*.50 + 100*2.69 + 100*1.50 = 569.00
        assertEquals(curExp+569.00, bus1.getExpenses());


        //customer 2 order items
        ArrayList<MenuItem> customer2Order = new ArrayList<>();
        customer2Order.add(burger);
        customer2Order.add(burger);
        customer2Order.add(burger);

        //customer 2 orders
        bus1.order(customer2Order, customer2, "1");

        //customer 3 order items
        ArrayList<MenuItem> customer3Order = new ArrayList<>();
        customer3Order.add(fries);

        //customer 3 order
        bus1.order(customer3Order, customer3, "2");

        //customer 4 order items
        ArrayList<MenuItem> customer4Order = new ArrayList<>();
        customer4Order.add(burger);
        customer4Order.add(burger);
        customer4Order.add(fries);
        customer4Order.add(fries);

        //customer 4 order
        bus1.order(customer4Order, customer4, "3");

        //customer 4 fake order (test item not on menu)
        ArrayList<MenuItem> customer4FakeOrder = new ArrayList<>();
        customer4FakeOrder.add(burger2);

        //second fake order for ordering item with an ingredient not in stock
        ArrayList<MenuItem> customer4FakeOrder2 = new ArrayList<>();
        customer4FakeOrder2.add(bacon);

        //check that items are getting decremented from inventory (not under threshold since all items in same order, and order didn't start under threshold
        assertEquals(243, testItem1.getCount());
        assertEquals(243, testItem2.getCount());
        assertEquals(243, testItem3.getCount());
        assertEquals(245, testItem4.getCount());


        //check order ID's
        assertEquals("0" , bus1.getAllOrders().get("0").getOrderID());
        assertEquals("1" ,bus1.getAllOrders().get("1").getOrderID());
        assertEquals("2" , bus1.getAllOrders().get("2").getOrderID());
        assertEquals("3" , bus1.getAllOrders().get("3").getOrderID());

        //check items are correct
        assertEquals("Burger, Fries" , bus1.getAllOrders().get("0").getItemNames());
        assertEquals("Burger, Burger, Burger" ,  bus1.getAllOrders().get("1").getItemNames());
        assertEquals("Fries" ,  bus1.getAllOrders().get("2").getItemNames());
        assertEquals("Burger, Burger, Fries, Fries" ,  bus1.getAllOrders().get("3").getItemNames());

        //check totals are correct
        assertEquals(8, bus1.getAllOrders().get("0").getTotal());
        assertEquals(15 , bus1.getAllOrders().get("1").getTotal());
        assertEquals(3, bus1.getAllOrders().get("2").getTotal());
        assertEquals(16, bus1.getAllOrders().get("3").getTotal());

        //check exception thrown for ordered item not on menu
        assertThrows(ItemDoesNotExistsException.class, ()-> bus1.order(customer4FakeOrder, customer4, "3"));

        //check exception thrown for ordering item with ingredient out of stock
        assertThrows(ItemCountAt0Exception.class, ()-> bus1.order(customer4FakeOrder2, customer4, "3"));
    }
}
