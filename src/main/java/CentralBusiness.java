import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CentralBusiness {

    private double revenue;
    private double expenses;
    private String businessName;
    private Map<String, Employee> employeesMap;
    private Inventory inventory;
    private HashMap<String, Order> allOrders;
    private HashMap<String, MenuItem> menu;
    private HashMap<String, Double> menuItemRevenue;
    private int reorderAmount; //amount to reorder when hits threshold
    private int inventoryThreshold; //buy more product when count reaches this threshold

    public CentralBusiness(String businessName) {
        this.businessName = businessName;
        this.employeesMap = new HashMap<String, Employee>();
        this.inventory = new Inventory();
        this.allOrders = new HashMap<String, Order>();
        this.menu = new HashMap<String, MenuItem>();
        this.menuItemRevenue = new HashMap<String, Double>();
        this.revenue = 0;
        this.expenses = 0;
    }

    public CentralBusiness() {
    }

    public HashMap<String, Order> getAllOrders() {
        return allOrders;
    }

    public Inventory getInventory() {

        return inventory;
    }

    /**
     * Description: Pass in Item id so we know which one to buy more
     * products of to increase amount of items
     */
    public void buyMoreProducts(String itemId, int amount) throws ItemDoesNotExistsException {
        if(!inventory.inventory.containsKey(itemId)){ //check is the item exists in the inventory
            throw new ItemDoesNotExistsException("No Item to buy products for");
        }
        addToExpenses(inventory.inventory.get(itemId).getCost() * amount);
       inventory.inventory.get(itemId).addCount(amount);
    }

    public HashMap<String, MenuItem> getMenu() {
        return menu;
    }

    public double getRevenue() {return revenue;}


    /**
     * Adds order total to revenue, called in order function in Customer class
     * @param orderPrice - price of customer order
     */
    public void addToRevenue(double orderPrice) {
        revenue+=orderPrice;
    }

    public void addToExpenses(double businessExpense){expenses+=businessExpense;}


    /**
     * creates a menu item for the business and sets it in  menu hash map.
     * @throws IllegalArgumentException if menuItem ID already exists
     * @throws IllegalArgumentException if price of menuitem isn't valid
     * @throws IllegalArgumentException if not all ingredients can be found in inventory
     * @param menuItem - the menu item to be added
     * @param inventory - inventory of the business
     * @return none
     */
    public void addToMenu(MenuItem menuItem, HashMap<String, Item> inventory){
        //check that it isn't a duplicate ID
        if (menu.containsKey(menuItem.getMenuID())) {
            throw new IllegalArgumentException("ID already exists in the menu");
        //check that the price is a valid amount
        } else if (!Item.isAmountValid(menuItem.getPrice())) {
            throw new IllegalArgumentException("Invalid price");
        //check that all ingredients exist in inventory
        } else if (!menuItem.checkIngredientsExist(inventory)) {
            throw new IllegalArgumentException("Ingredients not accounted for in inventory are in the menu item");
        //if all constraints pass, add menu item to menu
        } else {
            menu.put(menuItem.getMenuID(), menuItem);
            menuItemRevenue.put(menuItem.getMenuID(), 0.00);
        }

    }


    public Employee getEmployee(String id) throws IllegalArgumentException {
        if (!employeesMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee with id " + id + " doesn't exists");
        }
        return employeesMap.get(id);
    }

    public boolean exists(String id) {
        if (!employeesMap.containsKey(id)) {
            return false;
        }

        return true;

    }

    public void removeEmployee(String id) {
        if (!employeesMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee does not exist");
        }
        employeesMap.remove(id);
    }


    public void addAccount(String id, Employee employees) {
        if (employeesMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee ID already exists.");
        } else {
            employeesMap.put(id, employees);
        }
    }


    /**
     * Description: Calculates pay for employee based on their Id
     *
     * @param
     */
    public double calcPay(String id) throws IllegalArgumentException {

        if (!employeesMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee with id " + id + " doesn't exists");
        } else {
            double hours = employeesMap.get(id).getHoursWorked();
            double wage = employeesMap.get(id).getWage();
            double pay = 0;
            if (hours <= 40) {
                pay = wage * hours;
            } else {
                pay = (wage * 40) + ((hours - 40) * wage * 1.5);
            }


            DecimalFormat newFormat = new DecimalFormat("#. ##");


            pay = Double.valueOf(newFormat.format(pay));
            addToExpenses(pay);


            return pay;
        }

    }


    /**
     *
     */
    public double calcOvertimePay(String id, double hours) {
        hours = employeesMap.get(id).getHoursWorked() - 40;
        double wage = employeesMap.get(id).getWage() * 1.5;

        double overtimePay = wage * hours;

        return overtimePay;


    }



    /**
     *
     */
    public double getExpenses() {


        return expenses;
    }


    /**
     * @throws ItemDoesNotExistsException if menu item does not exist
     * @param menuItem
     * @return the revenue generated for a particular item
     */
    public double getRevenueByItem(MenuItem menuItem) throws ItemDoesNotExistsException {
        if (!menu.containsKey(menuItem.getMenuID())) {
            throw new ItemDoesNotExistsException(menuItem.getMenuItemName() + " does not exist in menu");
        } else {
            return menuItemRevenue.get(menuItem.getMenuID());
        }
    }


    public void setRevenueByItem(MenuItem menuItem, double rev) throws ItemDoesNotExistsException{
        if (!menu.containsKey(menuItem.getMenuID())) {
            throw new ItemDoesNotExistsException(menuItem.getMenuItemName() + " does not exist in menu");
        } else {
            double prevRev = menuItemRevenue.get(menuItem.getMenuID());
            menuItemRevenue.put(menuItem.getMenuID(), prevRev+rev);
        }
    }

    public void order(ArrayList<MenuItem> orderItems, Customer customer, String orderID) throws ItemDoesNotExistsException, ItemCountAt0Exception {
        //throw an exception if they order an item not on the menu or ingredients used are out of stock
        //iterate through menu items ordred
        for (int m = 0; m < orderItems.size(); m++) {
            if (!menu.containsKey(orderItems.get(m).getMenuID())) {
                throw new ItemDoesNotExistsException(orderItems.get(m).getMenuItemName() + " does not exist in menu");
            }
            //iterate through ingredients
            for (int n = 0; n < orderItems.get(m).getItemIngredients().size(); n++) {
                //should never reach due to automatic reordering
                if (orderItems.get(m).getItemIngredients().get(n).getCount() < 1) {
                    throw new ItemCountAt0Exception("Sorry, we are out of "+orderItems.get(m).getItemIngredients().get(n)+ ", please order again!");
                }
                //automatic reordering
                else if (orderItems.get(m).getItemIngredients().get(n).getCount() < inventoryThreshold) {
                    buyMoreProducts(orderItems.get(m).getItemIngredients().get(n).getItemID(), reorderAmount);
                }
            }
        }

        //create new order
        Order newOrder = new Order(customer.getCustomerID(), orderID, 0);

        //set ordered items to array of customer's menu items they want
        newOrder.setOrderedItems(orderItems);

        //create list for ingredients
        ArrayList<Item> ingredients = new ArrayList<>();

        //create variable that is total of order
        double tot = 0;

        for (int x = 0; x < orderItems.size(); x++) {
            //iterate through and calculate total price of all menu items
            tot+=orderItems.get(x).getPrice();
            //add revenue for each individual revenue for their own revenue
            setRevenueByItem(orderItems.get(x), orderItems.get(x).getPrice());
            //iterate through and add all the ingredients for the menu items to the array
            ingredients.addAll(orderItems.get(x).getItemIngredients());
        }

        //set total
        newOrder.setTotal(tot);

        //put new order in map of all orders
        allOrders.put(orderID, newOrder);

        //decrement each ingredient used in menu items in order
        for (int y = 0; y<ingredients.size(); y++) {
           inventory.decrementItem(ingredients.get(y).getItemID());
        }

        //make sure the order gets added to revenue
        addToRevenue(tot);
    }

    public int getInventoryThreshold() {
        return inventoryThreshold;
    }

    public void setInventoryThreshold(int threshold) {
        this.inventoryThreshold = threshold;
    }

    public int getReorderAmount() {
        return reorderAmount;
    }

    public void setReorderAmount(int am) {
        this.reorderAmount = am;
    }

    public String dailyStatsToString(){
        //TODO
        return "";
    }

    public String quarterlyStatsToString(){
        //TODO
        return "";
    }

    public String yearlyStatsToString(){
        //TODO
        return "";
    }


}