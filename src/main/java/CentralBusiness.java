import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CentralBusiness {

    protected static double revenue;
    protected static double expenses;
    private String businessName;
    private Map<String, Employee> employeesMap;
    protected static Inventory inventory;
    protected static HashMap<String, Order> allOrders;
    private HashMap<String, MenuItem> menu;

    public CentralBusiness(String businessName) {
        this.businessName = businessName;
        this.employeesMap = new HashMap<String, Employee>();
        this.inventory = new Inventory();
        this.allOrders = new HashMap<String, Order>();
        this.menu = new HashMap<String, MenuItem>();
        this.revenue = 0;
        this.expenses = 0;
    }

    public CentralBusiness() {
    }

    /**
     * Description: Pass in Item id so we know which one to buy more
     * products of to increase amount of items
     */
    public static int buyMoreProducts(String itemId, int amount) throws ItemDoesNotExistsException {
        if(!inventory.inventory.containsKey(itemId)){ //check is the item exists in the inventory
            throw new ItemDoesNotExistsException("No Item to buy products for");
        }
        addToExpenses(inventory.inventory.get(itemId).getCost() * amount);
        return inventory.inventory.get(itemId).addCount(amount);
    }

    public HashMap<String, MenuItem> getMenu() {
        return menu;
    }



    /**
     * Adds order total to revenue, called in order function in Customer class
     * @param orderPrice - price of customer order
     */
    public static void addToRevenue(double orderPrice) {
        revenue+=orderPrice;
    }

    public static void addToExpenses(double businessExpense){expenses+=businessExpense;}


    public double getRevenue() {return revenue;}


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



}