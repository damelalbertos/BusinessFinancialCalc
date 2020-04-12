import java.util.ArrayList;
import java.util.HashMap;

public class Order {

    protected String orderID;
    protected String customerID;
    private ArrayList<MenuItem> orderedItems;
    private double total;






    public Order(String orderID, String customerID,  double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.total = 0;
        this.orderedItems = new ArrayList<>();

    }

    /**
     * Increment the order ID for a new order
     */
    public void incrementOrderID() {
        //if no ID is set
        if (this.orderID.equals("")) {
            this.orderID = "0";
        } else {
            //get int value of the string
            int val  = Integer.parseInt(this.orderID)+1;
            //reset ID's if over 999 or not yet set
            if (val > 999 || this.orderID.equals("")) {
                this.orderID = "0";
                //set ID equal to current value + 1
            } else {
                this.orderID = "" + val;
            }
        }
    }

    /**
     * Increment the customer ID for a new order
     */
    public void incrementCustomerID() {
        //if no ID is set
        if (this.customerID.equals("")) {
            this.customerID = "0";
        } else {
            //get int value of the string
            int val  = Integer.parseInt(this.customerID)+1;
            //reset ID's if over 999 or not yet set
            if (val > 999) {
                this.customerID = "0";
                //set ID equal to current value + 1
            } else {
                this.customerID = "" + val;
            }
        }
    }



    public String getCustomerID(){ return customerID;}

    public double getTotal() { return total;}

    public ArrayList getItems(){
        return orderedItems;
    }

    public String getOrderID(){ return orderID;}

    public void order(ArrayList<MenuItem> orderItems) {
        orderedItems = orderItems;
        for (int x = 0; x < orderItems.size(); x++) {
            total+=orderItems.get(0).getPrice();
        }

        Order order = new Order(orderID, customerID, total);
        CentralBusiness.allOrders.put(orderID, order);






    }






}
