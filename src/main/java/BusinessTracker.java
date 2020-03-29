import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class BusinessTracker {


    private int revenue;
    private int expenses;
    private String businessName;
    private Map<String, Employees> employeesMap = new HashMap<>();
    private HashMap<String, Item> inventory;
    private HashMap<String, Order> allOrders;
    private HashMap<String, Item> menu;

    public HashMap<String, Item> getMenu() {
        return menu;
    }


    public BusinessTracker(String businessName) {
        this.businessName = businessName;
        this.employeesMap = new HashMap<String, Employees>();
        this.inventory = new HashMap<String, Item>();
        this.allOrders = new HashMap<String, Order>();
        this.menu = new HashMap<String, Item>();
    }

    /**
     * creates a menu for the business and sets to menu hash map. replaces old one if exists
     *
     * @param menuItems - a map with a key of the itemID, and value of the item object itself
     * @return none
     */
    public void createMenu(HashMap<String, Item> menuItems) {
        menu.clear();
        menu = menuItems;

    }


    public Employees getEmployee(String id) throws IllegalArgumentException {
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

    public void removeEmployee(String id, Employees employee) {
        if (!employeesMap.containsKey(id)) {
            throw new IllegalArgumentException("Employee does not exist");
        }
        employeesMap.remove(id, employee);
    }


    public void addAccount(String id, Employees employees) {
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

            pay = Double.valueOf(newFormat.format(pay));


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
     * @param revenue
     */

    public void Revenue(int revenue) {
        this.revenue = revenue;
    }

    /**
     * @param expenses
     */
    public void Expenses(int expenses) {
        this.expenses = expenses;
    }


    /**
     *
     */
    public int getRevenue() {


        return revenue;
    }

    /**
     *
     */
    public int getExpenses() {


        return expenses;
    }


    /**
     *
     */
    public void customerOrder() {


    }


}