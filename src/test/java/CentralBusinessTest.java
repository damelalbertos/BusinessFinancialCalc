import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBusinessTest {

    @Test
    public void buyMoreItemsTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        //make Business
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 5, "Burger Patty", 4.00);
        CentralBusiness.inventory.addItem(testItem1);
        CentralBusiness.inventory.addItem(testItem2);
        CentralBusiness.inventory.addItem(testItem3);
        CentralBusiness.inventory.addItem(testItem4);

        Employee employee1 = new Employee("1000", 11.25, 36);
        Employee employee2 = new Employee("1001", 13.25, 50);
        Employee employee3 = new Employee("1002", 10, 40);



        //test that the method successfully adds to the inventory count
        assertEquals(10, CentralBusiness.buyMoreProducts("0001", 5));
        assertEquals(15, CentralBusiness.buyMoreProducts("0004", 10));
        assertEquals(105, CentralBusiness.buyMoreProducts("0002", 100));
        assertEquals(9, CentralBusiness.buyMoreProducts("0003", 4));

        //test when the specified item does not exist
        assertThrows(ItemDoesNotExistsException.class, () ->CentralBusiness.buyMoreProducts("0006", 10));
    }

    @org.junit.Test
    public void addToExpensesTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException {
        //make Business
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //create items
        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);

        //add items to the inventory
        CentralBusiness.inventory.addItem(testItem1);
        CentralBusiness.inventory.addItem(testItem2);

        //buyMoreProducts 1
        CentralBusiness.buyMoreProducts("0001", 5); //1.00 * 5
        assertEquals(5.0, CentralBusiness.expenses);

        //buyMoreProducts 2
        //tests that it adds to a previous addition to expenses
        CentralBusiness.buyMoreProducts("0002", 7);//0.50 * 7 + 5
        assertEquals(8.5, CentralBusiness.expenses);


        //create employees
        Employee employee1 = new Employee("1000", 11.25, 36);
        Employee employee2 = new Employee("1001", 13.25, 50);
        Employee employee3 = new Employee("1002", 10, 40);

        //add employees to system
        bus1.addAccount("1000", employee1);
        bus1.addAccount("1001", employee2);
        bus1.addAccount("1002", employee3);

        //calculate pay for first employee
        bus1.calcPay("1000"); //expenses is already $8.50, after calcpay should be 413.50
        assertEquals(413.5, CentralBusiness.expenses);

        //calculate pay for second employee
        double overtimeEmployeePay = bus1.calcPay("1001");
        assertEquals(413.5+overtimeEmployeePay, CentralBusiness.expenses);

    }

    @org.junit.Test
    public void addToRevenueTest() throws ItemAlreadyExistsException, ItemCountAt0Exception, ItemDoesNotExistsException{

        //set up a business with an inventory
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //create items (ingredients)
        Item testItem1 = new Item("0001", 25, "Buns", 1.00);
        Item testItem2 = new Item("0002", 25, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 25, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 25, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 25, "Coke", 1.00);
        Item testItem6 = new Item("0006", 25, "Chicken", 4.00);

        //add items to business's inventory system
        CentralBusiness.inventory.addItem(testItem1);
        CentralBusiness.inventory.addItem(testItem2);
        CentralBusiness.inventory.addItem(testItem3);
        CentralBusiness.inventory.addItem(testItem4);
        CentralBusiness.inventory.addItem(testItem5);

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
        bus1.addToMenu(menuItem1, CentralBusiness.inventory.getInventory());
        bus1.addToMenu(menuItem2, CentralBusiness.inventory.getInventory());

        //create 4 customers for ordering
        Customer customer1 = new Customer("Bobby", "Billy");

        Customer customer2 = new Customer("Beth", "Jackson");

        Customer customer3 = new Customer("Jimmy", "Jim");

        Customer customer4 = new Customer("Tim", "Smith");

        //Order items
        ArrayList<MenuItem> customer1Order = new ArrayList<>();
        customer1Order.add(menuItem1);

        //customer 1 orders
        customer1.order(customer1Order, "0", "0");

        //check that revenue gets summed correctly
        assertEquals(7, bus1.getRevenue());

        //another orders occur
        ArrayList<MenuItem> customer2Order = new ArrayList<>();
        customer2Order.add(menuItem2);
        customer2.order(customer2Order, "1", "1");

        //check that revenue gets summed correctly
        assertEquals(8.50, bus1.getRevenue());

        //another orders occur
        ArrayList<MenuItem> customer3Order = new ArrayList<>();
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem1);
        customer3Order.add(menuItem2);
        customer3.order(customer3Order, "2", "2");

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
        customer4.order(customer4Order, "3", "3");

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
        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 5, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 5, "Coke", 1.00);
        Item testItem6 = new Item("0006", 5, "Chicken", 4.00);

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

        Employee employee1 = new Employee("1000", 11.25, 36);
        Employee employee2 = new Employee("1001", 13.25, 50);
        Employee employee3 = new Employee("1002", 10, 40);
        Employee employee4 = new Employee("1003", 15.37, 27);
        Employee employee5 = new Employee("1004", 14.26, 42);
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

        Employee employee = new Employee("100", 14.26, 42);
        Employee employee2 = new Employee("1001", 13.25, 50);

        business.addAccount("100", employee);
        business.addAccount("1001", employee2);

        assertEquals(42.78, business.calcOvertimePay("100", 2));
        assertEquals(198.75, business.calcOvertimePay("1001", 10));

    }



    @Test
    public void removeAccount(){
        CentralBusiness bus1 = new CentralBusiness("Business1");


        Employee employee = new Employee("100", 14.26, 42);
        Employee employee2 = new Employee("1001", 13.25, 50);

        bus1.addAccount("100", employee);
        bus1.addAccount("1001", employee2);

        assertEquals("100", bus1.getEmployee("100").getId());
        assertEquals("1001", bus1.getEmployee("1001").getId());


        bus1.removeEmployee("100");

        assertThrows(IllegalArgumentException.class, ()->bus1.getEmployee("100").getId());

        bus1.removeEmployee("1001");

        assertThrows(IllegalArgumentException.class, ()->bus1.getEmployee("1001").getId());

    }
}
