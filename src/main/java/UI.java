import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class UI {

    public static void main(String[] args) throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception, IOException, EmptyInventoryException, EmptyEmployeesMapException, EmptyMenuException, ParseException {

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



            String quarterOne = "2020-3-31";
            String quarterTwo = "2020-6-30";
            String quarterThree = "2020-9-30";
            String quarterFour = "2020-12-31";

            Date q1 = sdf.parse(quarterOne);
            Date q2 = sdf.parse(quarterTwo);
            Date q3 = sdf.parse(quarterThree);
            Date q4 = sdf.parse(quarterFour);

            Date curr = sdf.parse(newDate);


            if(curr.equals(q1)){
                quarterOneRev = bus1.getRevenue();

            }

            if(curr.equals(q2)){
                quarterTwoRev = bus1.getRevenue();

            }

            if(curr.equals(q3)) {
                quarterThreeRev = bus1.getRevenue();

            }



            if(curr.equals(q4)){
                quarterFourRev = bus1.getRevenue();

            }

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
                String firstInput = scan.nextLine().toLowerCase();
                while (!firstInput.equals("stats") && !firstInput.equals("inventory") && !firstInput.equals("menu") && !firstInput.equals("employees") && !firstInput.equals("orders") && !firstInput.equals("quit")) {
                    System.out.println("Please enter a valid command!");
                    System.out.println("What would you like to view/enter? (Stats, Inventory, Menu, Employees, Orders)");
                    firstInput = scan.nextLine().toLowerCase();
                }
                if (firstInput.equalsIgnoreCase("quit")){
                    System.exit(0);
                }
                switch (firstInput.toLowerCase()) {

                    case "stats":
                        System.out.println("What stats would you like to view? (Daily, Quarterly, or Yearly)");
                        String secondInput = scan.nextLine().toLowerCase();
                        while (!secondInput.equals("daily") && !secondInput.equals("quarterly") && !secondInput.equals("yearly") && !secondInput.equals("quit")) {
                            System.out.println("Please enter a valid time period or quit!");
                            System.out.println("What stats would you like to view? (Daily, Quarterly, or Yearly)");
                            secondInput = scan.nextLine().toLowerCase();
                        }
                        if (secondInput.equalsIgnoreCase("quit")){
                            System.exit(0);
                        }
                        boolean check = true;
                        while (check) {
                            System.out.println("Enter which quarter earnings you would like to view or all (q1, q2, q3, q4, all)");

                            switch (secondInput.toLowerCase()) {
                                case "daily":
                                    //TODO
                                    //SHOW DAILY STATS
                                    check = false;
                                    break;
                                case "quarterly":
                                    //TODO
                                    String thirdInput = scan.nextLine();
                                    //ASK WHICH QUARTER
                                    //SHOW QUARTERLY STATS BASED ON SPECIFIED QUARTER
                                    boolean check2 = true;

                                    while(check2){


                                        switch (thirdInput.toLowerCase()) {
                                            case "q1":
                                                System.out.println("Quarter 1: " + quarterOneRev);
                                                check2 = false;
                                                break;

                                            case "q2":
                                                System.out.println("Quarter 2: " + quarterTwoRev);
                                                check2 = false;
                                                break;

                                            case "q3":
                                                System.out.println("Quarter 3: " + quarterThreeRev);
                                                check2 = false;
                                                break;

                                            case "q4":
                                                System.out.println("Quarter 4: " + quarterFourRev);
                                                check2 = false;
                                                break;

                                            case "all":
                                                System.out.println("Quarter 1: " + quarterOneRev);
                                                System.out.println("Quarter 2: " + quarterTwoRev);
                                                System.out.println("Quarter 3: " + quarterThreeRev);
                                                System.out.println("Quarter 4: " + quarterFourRev);
                                                check2 = false;
                                                break;


                                        }

                                        if(check2 = false){
                                            break;
                                        }

                                    }
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

                        }
                        break;




                        //INVENTORY KEEPS PRINTING OUT WHEN WE PRINT OUT YEARLY REVENUE
                    case "inventory":
                        System.out.println("There are " + bus1.getInventory().getInventoryCount() + " items in the inventory");
                        System.out.println("Here is your product inventory list:");
                        System.out.println(bus1.getInventory().getProductInventory());
                        System.out.println("Would you like to add or remove any inventory items? (or any other keys to go back)");
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
                                while (!Character.isLetterOrDigit(potentialId.charAt(0))){
                                    System.out.println("Please Enter a valid ID!");
                                    System.out.println("What is the desired Item ID?");
                                    potentialId = scan.nextLine();
                                }
                                newItem.setItemId(potentialId);

                                System.out.println("What is the desired Item Name?");
                                String name = scan.nextLine();
                                while (!Character.isLetterOrDigit(name.charAt(0))){
                                    System.out.println("Please Enter a valid name!");
                                    System.out.println("What is the desired Item Name?");
                                    name = scan.nextLine();
                                }
                                newItem.setItemName(name);

                                System.out.println("How many of this item?");
                                //ensure a positive int is entered
                                Scanner scan1  = new Scanner(System.in);
                                while (true) {
                                    try {
                                        newItem.setItemCount(scan1.nextInt());
                                        break;
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println("Invalid - cannot be negative!");
                                        System.out.println("How many of this item?");
                                        scan1  = new Scanner(System.in);
                                    } catch (InputMismatchException ime) {
                                        System.out.println("Invalid - must be an integer!");
                                        System.out.println("How many of this item?");
                                        scan1  = new Scanner(System.in);
                                    }

                                }

                                //ensure cost is valid
                                System.out.println("How much does this item cost?");
                                Scanner scan2  = new Scanner(System.in);
                                while (true) {
                                    try {
                                        newItem.setCost(scan2.nextDouble());
                                        break;
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println("Invalid - cannot be negative or more than 2 decimals!");
                                        System.out.println("How much does this item cost?");
                                        scan2 = new Scanner(System.in);
                                    } catch (InputMismatchException ime){
                                        System.out.println("Invalid - must be a number!");
                                        System.out.println("How much does this item cost?");
                                        scan2  = new Scanner(System.in);
                                    }
                                }

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
                        System.out.println("Would you like to add or remove a menu item? (or any other keys to go back)");
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
                                while (!Character.isLetterOrDigit(potentialId.charAt(0))){
                                    System.out.println("Please Enter a valid ID!");
                                    System.out.println("What is the desired menu item ID?");
                                    potentialId = scan.nextLine();
                                }
                                newMenuItem.setMenuItemId(potentialId);

                                System.out.println("What is the menu item's name?");
                                String name = scan.nextLine();
                                while (!Character.isLetterOrDigit(name.charAt(0))){
                                    System.out.println("Please Enter a valid name!");
                                    System.out.println("What is the desired Item Name?");
                                    name = scan.nextLine();
                                }
                                newMenuItem.setMenuItemName(name);

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

                                System.out.println("What should the price of " + newMenuItem.getMenuItemName() + " be?");
                                Scanner scan3  = new Scanner(System.in);
                                while (true) {
                                    try {
                                        newMenuItem.setPrice(scan3.nextDouble());
                                        break;
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println("Invalid - cannot be negative or more than 2 decimals!");
                                        System.out.println("What should the price of " + newMenuItem.getMenuItemName() + " be?");
                                        scan3 = new Scanner(System.in);
                                    } catch (InputMismatchException ime){
                                        System.out.println("Invalid - must be a number!");
                                        System.out.println("What should the price of " + newMenuItem.getMenuItemName() + " be?");
                                        scan3  = new Scanner(System.in);
                                    }
                                }


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
                        System.out.println("Would you like to add or remove an employee? (or any other keys to go back)");
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
                                while (!Character.isLetterOrDigit(potentialId.charAt(0))){
                                    System.out.println("Please Enter a valid ID!");
                                    System.out.println("What is the desired Employee ID?");
                                    potentialId = scan.nextLine();
                                }
                                newEmployee.setId(potentialId);
                                System.out.println("What is this employee's wage?");
                                Scanner scan4  = new Scanner(System.in);
                                while (true) {
                                    try {
                                        newEmployee.setWage(scan4.nextDouble());
                                        break;
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println("Invalid - cannot be negative or more than 2 decimals!");
                                        System.out.println("What is the employee's wage?");
                                        scan4 = new Scanner(System.in);
                                    } catch (InputMismatchException ime){
                                        System.out.println("Invalid - must be a number!");
                                        System.out.println("What is the employee's wage?");
                                        scan4  = new Scanner(System.in);
                                    }
                                }

                                System.out.println("How many hours has this employee worked?");
                                Scanner scan5  = new Scanner(System.in);
                                while (true) {
                                    try {
                                        newEmployee.setHoursWorked(scan5.nextDouble());
                                        break;
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println("Invalid - cannot be negative or more than 2 decimals!");
                                        System.out.println("How many hours has this employee worked?");
                                        scan5 = new Scanner(System.in);
                                    } catch (InputMismatchException ime){
                                        System.out.println("Invalid - must be a number!");
                                        System.out.println("How many hours has this employee worked?");
                                        scan5  = new Scanner(System.in);
                                    }
                                }

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


