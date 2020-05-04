import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class UI {

    public static void main(String[] args) throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception, IOException, EmptyInventoryException {

        CentralBusiness bus1 = new CentralBusiness("Business 1");
        Inventory inventory = new Inventory();


        List<Item> items =  JsonUtil.listFromJsonFile("src/test/setItems.json", Item.class);


        List<MenuItem>allMenuItems = JsonUtil.listFromJsonFile("src/test/setMenu.json", MenuItem.class);


        for (Item item : items) {
            System.out.println(item.getItemName());
            inventory.addItem(item);
        }






        //        bus1.addToMenu(burger, bus1.getInventory().getInventory());
//        bus1.addToMenu(fries, bus1.getInventory().getInventory());
//        bus1.addToMenu(bacon, bus1.getInventory().getInventory());


//        Item testItem1 = new Item("0001", 5000, "Buns", 1.00);
//        Item testItem2 = new Item("0002", 1000, "Lettuce", 0.50);
//        Item testItem3 = new Item("0003", 1000, "Bacon", 0.69);
//        Item testItem4 = new Item("0004", 1000, "Burger Patty", 4.00);
//        Item testItem5 = new Item("0005", 1000, "potato", 2.00);
//        bus1.getInventory().addItem(testItem1);
//        bus1.getInventory().addItem(testItem2);
//        bus1.getInventory().addItem(testItem3);
//        bus1.getInventory().addItem(testItem4);


        bus1.setInventoryThreshold(100);
        bus1.setReorderAmount(1000);



//        testInventory.addItem(testItem1);
//        testInventory.addItem(testItem2);
//        testInventory.addItem(testItem3);
//        testInventory.addItem(testItem4);
//        testInventory.addItem(testItem5);


        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(items.get(0));
        burgerIngredients.add(items.get(1));
        burgerIngredients.add(items.get(2));
        burgerIngredients.add(items.get(3));


       // add fries ingredients
        ArrayList<Item> friesIngredients = new ArrayList<>();
        friesIngredients.add(items.get(9));

        //create menuItem object (burger and fries)
        MenuItem burger = new MenuItem("0001", "Burger", 5.00);
        MenuItem fries = new MenuItem("0002", "Fries", 3.00);
        burger.setItemIngredients(burgerIngredients);
        fries.setItemIngredients(friesIngredients);

        //menuItem to test count < 1 ordering
        MenuItem bacon = new MenuItem("0003", "Bacon", 3.00);
        ArrayList<Item> baconIngredients = new ArrayList<>();
        baconIngredients.add(items.get(10));
        bacon.setItemIngredients(baconIngredients);




        MenuItem mozzarellaSticks = new MenuItem("0004", "Mozzarella Sticks", 6.00);
        ArrayList<Item> mozzarellaSticksIngredients = new ArrayList<>();
        mozzarellaSticksIngredients.add(items.get(2));
        mozzarellaSticks.setItemIngredients(mozzarellaSticksIngredients);


        MenuItem salad = new MenuItem("0005", "Salad", 8.00);
        ArrayList<Item> saladIngredients= new ArrayList<>();
        saladIngredients.add(items.get(2));
        salad.setItemIngredients(saladIngredients);


        //create menuItem object (burger and fries)
        MenuItem cheeseBurger = new MenuItem("0006", "CheeseBurger", 7.00);
        ArrayList<Item>cheeseBurgerIngredients = new ArrayList<>();
        cheeseBurgerIngredients.add(items.get(0));
        cheeseBurgerIngredients.add(items.get(1));
        cheeseBurgerIngredients.add(items.get(2));
        cheeseBurgerIngredients.add(items.get(3));
        cheeseBurgerIngredients.add(items.get(4));


        cheeseBurger.setItemIngredients(cheeseBurgerIngredients);

        allMenuItems.add(cheeseBurger);
        allMenuItems.add(bacon);
        allMenuItems.add(fries);
        allMenuItems.add(burger);
        allMenuItems.add(mozzarellaSticks);
        allMenuItems.add(salad);




        for (MenuItem menuItem : allMenuItems) {
            bus1.addToMenu(menuItem, bus1.getInventory().getInventory());

        }









        Employee employee1 = new Employee("1000", 11.25, 36);
        Employee employee2 = new Employee("1001", 13.25, 50);
        Employee employee3 = new Employee("1002", 10, 40);
        Employee employee4 = new Employee("1002", 20, 40);

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

            System.out.println("Would you like to view or enter stats?");
            //check that input is view or enter
            while(!scan.next().equalsIgnoreCase("enter") || !scan.next().equalsIgnoreCase("view")){
                System.out.println("Invalid, please type view, enter, or quit.");
            }
            //if input is view
            switch (scan.next().toLowerCase()){
                case "view":
                    System.out.println("What would you like to view? (Stats, Inventory, Menu, Employees)");
                    while(!scan.next().equalsIgnoreCase("stats") || !scan.next().equalsIgnoreCase("inventory") ||
                            !scan.next().equalsIgnoreCase("menu") || !scan.next().equalsIgnoreCase("employees")){
                        System.out.println("Invalid, please type stats, inventory, menu, employees, or quit.");
                    }
                    switch (scan.next().toLowerCase()){
                        case "stats":
                            System.out.println("Would you like to view daily, quarterly, or yearly stats?");
                            while(!scan.next().equalsIgnoreCase("daily") || !scan.next().equalsIgnoreCase("quarterly") ||
                                    !scan.next().equalsIgnoreCase("yearly")){
                                System.out.println("Invalid, please type daily, quarterly, yearly, or quit.");
                            }
                            switch (scan.next().toLowerCase()){
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
                                    break;
                                default:
                                    System.out.println("Hopefully this doesn't happen...ERROR");
                            }
                            break;
                        case "inventory":
                            bus1.getInventory().getProductInventory();
                            break;
                        case "menu":
                            //TODO
                            //ENTIRE MENU OR A SPECIFIC MENUITEM?
                            //IF ENTIRE
                                //LOOP THROUGH BUSINESS' MENU
                                //DISPLAY SPECIFIC INFO FOT EACH MENUITEM
                            //IF SPECIFIC
                                //ASK FOR MENUITEM ID AND DISPLAY INFO
                            break;
                        case "employees":
                            //TODO
                            //ALL EMPLOYEES OR A SPECIFIC ONE?
                            //IF ALL
                                //LOOP THROUGH EMPLOYEE MAP AND DISPLAY DATA
                            //IF SPECIFIC
                                //ASK FOR EMPLOYEE ID AND DISPLAY DATA
                            break;
                        default:
                            System.out.println("Hopefully this doesn't happen...ERROR");
                    }
                    break;
                case "enter":
                    //TODO
                    break;
                default:
                    System.out.println("Hopefully this doesn't happen...ERROR");
            }





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
}
