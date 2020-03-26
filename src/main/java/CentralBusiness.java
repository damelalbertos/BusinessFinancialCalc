import java.util.HashMap;

public class CentralBusiness {
    private String businessName;
    ;
    private HashMap<String, Employees> allEmployees;
    private HashMap<String, Item> inventory;
    private HashMap<String, Order> allOrders;
    private HashMap<String, Item> menu;


    public CentralBusiness(String businessName) {
        this.businessName = businessName;
        this.allEmployees = new HashMap<String, Employees>();
        this.inventory = new HashMap<String, Item>();
        this.allOrders = new HashMap<String, Order>();
        this.menu = new HashMap<String, Item>();
    }

        /**
         * creates a menu for the business and sets to menu hash map. replaces old one if exists
         * @param menuItems - a map with a key of the itemID, and value of the item object itself
         * @return none
         */
        public void createMenu(HashMap<String, Item> menuItems){
            menu.clear();
            menu = menuItems;

        }

        public HashMap<String, Item> getMenu() {
            return menu;
        }

}

