import java.util.Random;
import java.util.Scanner;




public class UI {

    public static void main(String[] args) throws ItemAlreadyExistsException {

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
        Employee employee4 = new Employee("1002", 20, 40);

        bus1.addAccount("1000", employee1);
        bus1.addAccount("1001", employee2);
        bus1.addAccount("1002", employee3);

        Scanner scan = new Scanner(System.in);


        System.out.println("Hello, please enter any key or quit");


        String j = scan.next();
        while (!j.equals("quit")) {

            System.out.println("Please enter employee id");

            if (!bus1.exists(scan.next())) {
                System.out.println("ID is invalid");
            } else {

                System.out.println("What would you like to do?");

                if (scan.next().equals("order")) {
                    System.out.println("Enter first name and last name");
                    new Customer(scan.next(), scan.next());
                    //new Order(random number 1-1000, random number 1-1000, total = 0);

                }


                System.out.println("Would you like to continue? type quit or any key");

                if(scan.next().equals("quit")){
                    break;
                }



            }




        }
    }
}
