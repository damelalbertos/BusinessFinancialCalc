import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class UI {

    public static void main(String[] args) throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception, IOException, EmptyInventoryException, EmptyEmployeesMapException, EmptyMenuException {

        //create business
        CentralBusiness bus1 = new CentralBusiness("Business 1");

        //read ingredients from json file
        List<Item> items = JsonUtil.listFromJsonFile("src/test/setItems.json", Item.class);

        //read menu items from json file
        List<MenuItem> allMenuItems = JsonUtil.listFromJsonFile("src/test/setMenu.json", MenuItem.class);

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


        ArrayList<Item> saladIngredients = new ArrayList<>();
        saladIngredients.add(items.get(2));


        ArrayList<Item> cheeseBurgerIngredients = new ArrayList<>();
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
        employee4.setWage(20);
        employee4.setHoursWorked(40);

        //add employees to business
        bus1.addAccount("1000", employee1);
        bus1.addAccount("1001", employee2);
        bus1.addAccount("1002", employee3);


        Random rand = new Random();

        String quarterOne = "2020-4-14";
        String quarterTwo = "2020-7-14";
        String quarterThree = "2020-10-14";
        String quarterFour = "2020-1-14";

        double quarterOneRev = 0;
        double quarterTwoRev = 0;
        double quarterThreeRev = 0;
        double quarterFourRev = 0;

        String oldDate = "2020-01-01";
        //System.out.println("Date before Addition: "+oldDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Incrementing the date by 1 day
        c.add(Calendar.DAY_OF_MONTH, 1);
        String newDate = sdf.format(c.getTime());
        //System.out.println("Date Incremented by One: "+newDate);


        String endDate = "2020-12-31";

        while (newDate.compareTo(endDate) != 0) {
            int orderAmount = rand.nextInt((100 - 25) + 1) + 25;
            int counter = 0;

            while (counter < orderAmount) {
                Customer customer = new Customer("useJava", "Faker", "0");
                ArrayList<MenuItem> customerOrder = new ArrayList<>();

                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                customerOrder.add(allMenuItems.get(new Random().nextInt(allMenuItems.size())));
                bus1.order(customerOrder, customer, "1");

                //System.out.println(counter);


                //create new objects for customer orders
                //pick up to 10 random menuItems to chooose from
                //get total and all that to add automatically to business
                counter++;
            }

            //Incrementing the date by 1 day
            c.add(Calendar.DAY_OF_MONTH, 1);
            newDate = sdf.format(c.getTime());

            //add one day to newDate and continue the process over and over until that date is reached


//            if(newDate.compareTo(quarterOne) == 0){
//                quarterOneRev = bus1.getRevenue();
//
//
//                System.out.println(quarterOneRev);
//
//            }

        }


        Scanner scan = new Scanner(System.in);


        System.out.println("Hi! Welcome to Business Financial Calculator.");
        System.out.println("To exit the program, type 'quit' at anytime");
        System.out.println();

        //String j = scan.next();
        while (true) {
            boolean again = true;

            while (again) {
                System.out.println("What would you like to view/enter? (Stats, Inventory, Menu, Employees, Orders)");
                String firstInput = scan.nextLine();
                if (firstInput.equalsIgnoreCase("quit")){
                    System.exit(0);
                }
                switch (firstInput.toLowerCase()) {

                    case "stats":
                        System.out.println("What stats would you like to view? (Daily, Quarterly, or Yearly)");
                        String secondInput = scan.nextLine();
                        if (secondInput.equalsIgnoreCase("quit")){
                            System.exit(0);
                        }
                        boolean check = true;
                        while (check) {
                            switch (secondInput.toLowerCase()) {
                                case "daily":
                                    //TODO
                                    //SHOW DAILY STATS
                                    check = false;
                                    break;
                                case "quarterly":
                                    //TODO
                                    //ASK WHICH QUARTER
                                    //SHOW QUARTERLY STATS BASED ON SPECIFIED QUARTER
                                    check = false;
                                    break;
                                case "yearly":
                                    check = false;
                                    //TODO
                                    //SHOW YEARLY STATS
                                    System.out.println("Revenue: " + bus1.getRevenue());
                                    System.out.println("Expenses: " + bus1.getExpenses());
                                    break;
                            }
                            if (!check) {
                                break;
                        }
                            System.out.println("invalid command, please enter daily, quarterly, or yearly");

                        }
                        break;




                        //INVENTORY KEEPS PRINTING OUT WHEN WE PRINT OUT YEARLY REVENUE
                    case "inventory":
                        System.out.println("There are " + bus1.getInventory().getInventoryCount() + " items in the inventory");
                        System.out.println("Here is your product inventory list:");
                        System.out.println(bus1.getInventory().getProductInventory());
                        System.out.println("Would you like to add or remove any inventory items?");
                        String inventoryInput = scan.nextLine();
                        if (inventoryInput.equalsIgnoreCase("quit")){
                            System.exit(0);
                        }
                        switch (inventoryInput.toLowerCase()){
                            case "add":
                                Item newItem = new Item();
                                System.out.println("What is the desired Item ID?");
                                String potentialId = scan.nextLine();
                                while (bus1.getInventory().itemExistsAlready(potentialId)){
                                    System.out.println("That ID is already in use, please use another one.");
                                    potentialId = scan.nextLine();
                                }
                                newItem.setItemId(potentialId);
                                System.out.println("What is the desired Item Name?");
                                newItem.setItemName(scan.nextLine());
                                System.out.println("How many of this item?");
                                newItem.setItemCount(scan.nextInt());
                                System.out.println("How much does this item cost?");
                                newItem.setCost(scan.nextDouble());
                                System.out.println("Item Added!");
                                System.out.println("Here is your new item!");
                                System.out.println(newItem.itemToString());
                                bus1.getInventory().addItem(newItem);
                                break;
                            case "remove":
                                System.out.println("What is the ID of the item?");
                                String toBeRemoved = scan.nextLine();
                                while (!bus1.getInventory().itemExistsAlready(toBeRemoved)){
                                    System.out.println("That ID does not exist, please use another one.");
                                    toBeRemoved = scan.nextLine();
                                }
                                bus1.getInventory().removeItem(toBeRemoved);
                                System.out.println("Item Removed!");
                                break;
                        }

                        again = false;
                        break;


                    case "menu":
                        //TODO

                        //Print all MenuItems
                        System.out.println("Here is your menu:");
                        System.out.println(bus1.menuToString());
                        System.out.println("Would you like to add or remove a menu item?");
                        String menuInput = scan.nextLine();
                        if (menuInput.equalsIgnoreCase("quit")){
                            System.exit(0);
                        }
                        switch (menuInput.toLowerCase()){
                            case "add":
                                MenuItem newMenuItem = new MenuItem();
                                ArrayList<Item> ingredients = new ArrayList<>();
                                System.out.println("What is the desired menu item ID?");
                                String potentialId = scan.nextLine();
                                while (bus1.menuItemExistsAlready(potentialId)){
                                    System.out.println("That ID is already in use, please use another one.");
                                    potentialId = scan.nextLine();
                                }
                                newMenuItem.setMenuItemId(potentialId);
                                System.out.println("What is the menu item's name?");
                                newMenuItem.setMenuItemName(scan.nextLine());
                                System.out.println("Now we will add ingredients, here is the list of current items:");
                                System.out.println(bus1.getInventory().getProductInventory());
                                System.out.println("Type an item ID/ingredient of the menu item or done once finished.");
                                String ingredient = scan.nextLine();
                                while (!ingredient.equalsIgnoreCase("done")){
                                    Item toBeAdded = bus1.getInventory().getItem(ingredient);
                                    ingredients.add(toBeAdded);
                                    ingredient = scan.nextLine();
                                }
                                newMenuItem.setItemIngredients(ingredients);
                                System.out.println("What is the price of " + newMenuItem.getMenuItemName() + "?");
                                newMenuItem.setPrice(scan.nextDouble());
                                bus1.addToMenu(newMenuItem, bus1.getInventory().getInventory());
                                System.out.println("Menu Item Added!");
                                break;

                            case "remove":
                                System.out.println("What is the ID of the Menu Item?");
                                String toBeRemoved = scan.nextLine();
                                while (!bus1.menuItemExistsAlready(toBeRemoved)){
                                    System.out.println("That ID does not exist, please use another one.");
                                    toBeRemoved = scan.nextLine();
                                }
                                bus1.removeMenuItem(toBeRemoved);
                                System.out.println("Menu Item Removed!");
                                break;
                        }
                        again = false;
                        break;

                    case "employees":
                        System.out.println("Here are your employees:");
                        System.out.println(bus1.employeeDataToString());
                        System.out.println("Would you like to add or remove an employee?");
                        String employeesInput = scan.nextLine();
                        if (employeesInput.equalsIgnoreCase("quit")){
                            System.exit(0);
                        }
                        switch (employeesInput.toLowerCase()){
                            case "add":
                                Employee newEmployee = new Employee();
                                System.out.println("What is the desired Employee ID?");
                                String potentialId = scan.nextLine();
                                while (bus1.employeeExistsAlready(potentialId)){
                                    System.out.println("That ID is already in use, please use another one.");
                                    potentialId = scan.nextLine();
                                }
                                newEmployee.setId(potentialId);
                                System.out.println("What is this employee's wage?");
                                newEmployee.setWage(scan.nextDouble());
                                System.out.println("How many hours has this employee worked");
                                newEmployee.setHoursWorked(scan.nextDouble());
                                bus1.addAccount(newEmployee.getId(), newEmployee);
                                System.out.println("Employee Added!");
                                break;
                            case "remove":
                                System.out.println("What is the ID of the Employee?");
                                String toBeRemoved = scan.nextLine();
                                while (!bus1.employeeExistsAlready(toBeRemoved)){
                                    System.out.println("That ID does not exist, please use another one.");
                                    toBeRemoved = scan.nextLine();
                                }
                                bus1.removeEmployee(toBeRemoved);
                                System.out.println("Employee Removed!");
                                break;
                        }
                        again = false;
                        break;

                    case "orders":
                        try {
                            System.out.println(bus1.allOrdersToString());
                        } catch (EmptyOrdersMapException e) {
                            System.out.println("There are no orders to show!");
                        }
                        break;
                }
                if (!again) {
                    break;
                }
            }
        }
    }
}

   // }
//}


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
