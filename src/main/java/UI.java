import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class UI {

    public static void main(String[] args) throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception {

        CentralBusiness bus1 = new CentralBusiness("Business 1");
        Inventory testInventory = new Inventory();


        Item testItem1 = new Item("0001", 5000, "Buns", 1.00);
        Item testItem2 = new Item("0002", 1000, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 1000, "Bacon", 0.69);
        Item testItem4 = new Item("0004", 1000, "Burger Patty", 4.00);
        Item testItem5 = new Item("0005", 1000, "potatoe", 2.00);
        bus1.getInventory().addItem(testItem1);
        bus1.getInventory().addItem(testItem2);
        bus1.getInventory().addItem(testItem3);
        bus1.getInventory().addItem(testItem4);



        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);
        testInventory.addItem(testItem5);


        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);
        burgerIngredients.add(testItem4);

        //add fries ingredients
        ArrayList<Item> friesIngredients = new ArrayList<>();
        friesIngredients.add(testItem4);

        //create menuItem object (burger and fries)
        MenuItem burger = new MenuItem("0001", "Burger", 5.00);
        MenuItem fries = new MenuItem("0002", "Fries", 3.00);
        burger.setItemIngredients(burgerIngredients);
        fries.setItemIngredients(friesIngredients);

        //menuItem to test count < 1 ordering
        MenuItem bacon = new MenuItem("0003", "Bacon", 3.00);
        ArrayList<Item> baconIngredients = new ArrayList<>();
        bacon.setItemIngredients(baconIngredients);



        bus1.addToMenu(burger, bus1.getInventory().getInventory());
        bus1.addToMenu(fries, bus1.getInventory().getInventory());
        bus1.addToMenu(bacon, bus1.getInventory().getInventory());





       ArrayList<MenuItem>allMenuItems = new ArrayList<>();
        allMenuItems.add(fries);
        allMenuItems.add(bacon);
        allMenuItems.add(burger);





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




        }

        Scanner scan = new Scanner(System.in);


        System.out.println("Hello, please enter any key or quit");


        String j = scan.next();
        while (!j.equals("quit")) {

            System.out.println("Please enter employee id");

            if (!bus1.exists(scan.next())) {
                System.out.println("ID is invalid");
            } else {

                System.out.println("What would you like to do?");

//                if (scan.next().equals("order")) {
//                    System.out.println("Enter first name and last name");
//                    Customer customer = new Customer(scan.next(), scan.next());
//
//
//                    //new Order(random number 1-1000, random number 1-1000, total = 0);
//
//                }

                String i = scan.next();
                if(i.equals("revenue")){
                    System.out.println(bus1.getRevenue());
                }



                if(i.equals("expenses")){
                    System.out.println(bus1.getExpenses());
                }

                if(i.equals("employee")){
                    System.out.println("Would you like to add, remove or calculate pay");

                    if(scan.next().equals("add")){
                        System.out.println("Enter new ID");
                        String id = scan.next();
                        System.out.println("Enter wage");
                        double wage = scan.nextDouble();
                        System.out.println("Enter hoursWorked");
                        int hoursWorked= scan.nextInt();
                        Employee employee = new Employee(id, wage, hoursWorked);
                        bus1.addAccount(id, employee);
                    }

                    if(i.equals("remove")){
                        System.out.println("Enter id for Employee to remove");
                        String id= scan.next();


                        if(bus1.getEmployee(id) != null) {
                            bus1.removeEmployee(id);
                        }

                        else{
                            System.out.println("Employee Doesn't exist");
                        }

                    }

                    if(i.equals("pay")){

                    }
                }
                System.out.println("Would you like to continue? type quit or any key");

                if(i.equals("quit")){
                    break;
                }



            }




        }
    }
}
