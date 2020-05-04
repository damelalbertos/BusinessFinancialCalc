import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class UI {

    public static void main(String[] args) throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception, IOException, EmptyInventoryException {

        //create business
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //read ingredients from json file
        List<Item> items =  JsonUtil.listFromJsonFile("src/test/setItems.json", Item.class);

        //read menu items from json file
        List<MenuItem>allMenuItems = JsonUtil.listFromJsonFile("src/test/setMenu.json", MenuItem.class);

        //add ingredients to business's inventory
        for (Item item : items) {
            bus1.getInventory().addItem(item);
        }

        //set automatic re-ordering threshold and amount
        bus1.setInventoryThreshold(100);
        bus1.setReorderAmount(1000);

        //add ingredients for different menu items
        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(items.get(0));
        burgerIngredients.add(items.get(1));
        burgerIngredients.add(items.get(2));

        ArrayList<Item> friesIngredients = new ArrayList<>();
        friesIngredients.add(items.get(9));

        ArrayList<Item> mozzarellaSticksIngredients = new ArrayList<>();
        mozzarellaSticksIngredients.add(items.get(2));


        ArrayList<Item> saladIngredients= new ArrayList<>();
        saladIngredients.add(items.get(2));


        ArrayList<Item>cheeseBurgerIngredients = new ArrayList<>();
        cheeseBurgerIngredients.add(items.get(0));
        cheeseBurgerIngredients.add(items.get(1));
        cheeseBurgerIngredients.add(items.get(2));
        cheeseBurgerIngredients.add(items.get(3));
        cheeseBurgerIngredients.add(items.get(4));

        //set each menu item's ingredients
        allMenuItems.get(0).setItemIngredients(burgerIngredients);
        allMenuItems.get(1).setItemIngredients(friesIngredients);
        allMenuItems.get(2).setItemIngredients(cheeseBurgerIngredients);
        allMenuItems.get(3).setItemIngredients(mozzarellaSticksIngredients);
        allMenuItems.get(4).setItemIngredients(saladIngredients);

        //add all menu items to the business's menu
        for (MenuItem menuItem : allMenuItems) {
            bus1.addToMenu(menuItem, bus1.getInventory().getInventory());

        }

        //create employees
        Employee employee1 = new Employee("1000", 11.25, 36);
        Employee employee2 = new Employee("1001", 13.25, 50);
        Employee employee3 = new Employee("1002", 10, 40);
        Employee employee4 = new Employee("1002", 20, 40);

        //add employees to business
        bus1.addAccount("1000", employee1);
        bus1.addAccount("1001", employee2);
        bus1.addAccount("1002", employee3);


        Random rand = new Random();

        String oldDate = "2020-01-01";
        //System.out.println("Date before Addition: "+oldDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(oldDate));
        }catch(ParseException e){
            e.printStackTrace();
        }

        //Incrementing the date by 1 day
        c.add(Calendar.DAY_OF_MONTH, 1);
        String newDate = sdf.format(c.getTime());
        //System.out.println("Date Incremented by One: "+newDate);



        String endDate = "2020-12-31";

        while(newDate.compareTo(endDate) != 0){
            int orderAmount = rand.nextInt((100-25)+1)+25;
            int counter =0;

            while(counter < orderAmount){
                Customer customer = new Customer("useJava", "Faker", "0");
                ArrayList<MenuItem> customerOrder = new ArrayList<>();

                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                bus1.order(customerOrder, customer, "1");

                System.out.println(counter);



                //create new objects for customer orders
                //pick up to 10 random menuItems to chooose from
                //get total and all that to add automatically to business
                counter++;
            }

            //Incrementing the date by 1 day
            c.add(Calendar.DAY_OF_MONTH, 1);
            newDate = sdf.format(c.getTime());
            //add one day to newDate and continue the process over and over until that date is reached




        }




        Scanner scan = new Scanner(System.in);


        System.out.println("Hi! Welcome to Business Financial Calculator. Press any key to begin.");
        System.out.println("To exit the program, type 'quit' at anytime");


        //String j = scan.next();
        while (!scan.next().equalsIgnoreCase("quit")) {

            // System.out.println("Would you like to view or enter stats?");
            //check that input is view or enter
//            while(!scan.next().equalsIgnoreCase("enter") || !scan.next().equalsIgnoreCase("view")){
//                System.out.println("Invalid, please type view, enter, or quit.");
//            }
            //if input is viewe
            //  switch (scan.next().toLowerCase()){
            //   case "view":
            System.out.println("What would you like to view? (Stats, Inventory, Menu, Employees)");
//                    while(!scan.next().equalsIgnoreCase("stats") || !scan.next().equalsIgnoreCase("inventory") ||
//                            !scan.next().equalsIgnoreCase("menu") || !scan.next().equalsIgnoreCase("employees")){
//                        System.out.println("Invalid, please type stats, inventory, menu, employees, or quit.");
            //}

            switch (scan.next().toLowerCase()) {
                case "stats":
                    System.out.println("Would you like to view daily, quarterly, or yearly stats?");
//                    while (!scan.next().equalsIgnoreCase("daily") || !scan.next().equalsIgnoreCase("quarterly") ||
//                            !scan.next().equalsIgnoreCase("yearly")) {
//                        System.out.println("Invalid, please type daily, quarterly, yearly, or quit.");

                    //case "revenue":
                    switch (scan.next().toLowerCase()) {
                        case "daily":
                            //TODO
                            //SHOW DAILY STATS
                            break;
                        case "quaterly":
                            //TODO
                            //ASK WHICH QUARTER
                            //SHOW QUARTERLY STATS BASED ON SPECIFIED QUARTER
                            break;
                        case "yearly":
                            //TODO
                            //SHOW YEARLY STATS
                            System.out.println(bus1.getRevenue());
                            break;

                        default:
                            System.out.println("Hopefully this doesn't happen...ERROR");
                    }
                    break;



                case "inventory":
                    System.out.println("There are " + bus1.getInventory().getInventoryCount()+ " items in the inventory");
                    System.out.println(bus1.getInventory().getProductInventory());
                        break;



                case "menu":
                    //TODO
                    //ENTIRE MENU OR A SPECIFIC MENUITEM?
                    //IF ENTIRE
                    //LOOP THROUGH BUSINESS' MENU
                    //DISPLAY SPECIFIC INFO FOT EACH MENUITEM
                    //IF SPECIFIC
                    //ASK FOR MENUITEM ID AND DISPLAY INFO


                    //Print all MenuItems
                    for (MenuItem menuItem : allMenuItems) {
                        System.out.println(menuItem.getMenuItemName());

                    }
                    break;

                case "employees":
                    //TODO
                    //ALL EMPLOYEES OR A SPECIFIC ONE?
                    //IF ALL
                    //LOOP THROUGH EMPLOYEE MAP AND DISPLAY DATA
                    //IF SPECIFIC
                    //ASK FOR EMPLOYEE ID AND DISPLAY DATA


                    //add Employee


                    break;
                default:
                    // System.out.println("Hopefully this doesn't happen...ERROR");
                    System.out.println("invalid command, please enter employees or  stats");


            }
                break;
            }
                //case "enter":
//                    //TODO
//                    break;
//                default:
//                    //System.out.println("Hopefully this doesn't happen...ERROR");
//                    System.out.println("invalid command, please enter view or stats");
//            }








//            System.out.println("Please enter employee id");
//
//            if (!bus1.exists(scan.next())) {
//                System.out.println("ID is invalid");
//            } else {
//
//                System.out.println("What would you like to do?");
//
//
//                String i = scan.next();
//                if(i.equals("revenue")){
//                    System.out.println(bus1.getRevenue());
//                }
//
//
//
//                if(i.equals("expenses")){
//                    System.out.println(bus1.getExpenses());
//                }
//
//                if(i.equals("employee")){
//                    System.out.println("Would you like to add, remove or calculate pay");
//
//                    if(scan.next().equals("add")){
//                        System.out.println("Enter new ID");
//                        String id = scan.next();
//                        System.out.println("Enter wage");
//                        double wage = scan.nextDouble();
//                        System.out.println("Enter hoursWorked");
//                        int hoursWorked= scan.nextInt();
//                        Employee employee = new Employee(id, wage, hoursWorked);
//                        bus1.addAccount(id, employee);
//                    }
//
//                    if(i.equals("remove")){
//                        System.out.println("Enter id for Employee to remove");
//                        String id= scan.next();
//
//
//                        if(bus1.getEmployee(id) != null) {
//                            bus1.removeEmployee(id);
//                        }
//                        else{
//                            System.out.println("Employee Doesn't exist");
//                        }
//                    }
//                    if(i.equals("pay")){
//
//                    }
//                }
//                System.out.println("Would you like to continue? type quit or any key");
//
//                if(i.equals("quit")){
//                    break;
//                }
//            }
        }
    }
