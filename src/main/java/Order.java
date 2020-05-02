import java.util.ArrayList;

public class Order {

    private String orderID;
    private String customerID;
    private ArrayList<MenuItem> orderedItems;
    private double total;


    public Order(String orderID, String customerID, double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.total = 0;
        this.orderedItems = new ArrayList<>();

    }

    /**
     * Increment the order ID for a new order
     * (Not yet applied to ordering)
     */
    public String incrementOrderID() {
        //if no ID is set
        if (this.orderID.equals("")) {
            this.orderID = "0";
        } else {
            //get int value of the string
            int val = Integer.parseInt(this.orderID) + 1;
            //reset ID's if over 999 or not yet set
            if (val > 999 || this.orderID.equals("")) {
                this.orderID = "0";
                //set ID equal to current value + 1
            } else {
                this.orderID = "" + val;
            }
        }
        return this.orderID;
    }

    /**
     * Increment the customer ID for a new order
     * (Not yet applied to ordering)
     */
    public String incrementCustomerID() {
        //if no ID is set
        if (this.customerID.equals("")) {
            this.customerID = "0";
        } else {
            //get int value of the string
            int val = Integer.parseInt(this.customerID) + 1;
            //reset ID's if over 999 or not yet set
            if (val > 999) {
                this.customerID = "0";
                //set ID equal to current value + 1
            } else {
                this.customerID = "" + val;
            }
        }
        return this.customerID;
    }


    public String getCustomerID() {
        return customerID;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<MenuItem> getItems() {
        return orderedItems;
    }

    public String getItemNames() {
        String str = "";
        for (int x = 0; x < orderedItems.size(); x++) {
            str+=orderedItems.get(x).getMenuItemName() + ", ";
        }
        return str.substring(0, str.length()-2);
    }

    public String getOrderID() {
        return orderID;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    public void setOrderedItems(ArrayList<MenuItem> orderItems) {
        this.orderedItems = orderItems;
    }


   
}



