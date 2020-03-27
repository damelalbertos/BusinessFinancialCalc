import java.util.ArrayList;
import java.util.HashMap;

public class Order {

    private String orderID;
    private String customerID;
    private ArrayList<Item> orderedItems;
    private double total;

    public Order(String orderID, String customerID, ArrayList<Item> orderedItems, double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderedItems = orderedItems;
        this.total = total;
    }
    public String getCustomerID(){ return customerID;}

    public double getTotal() { return total;}

    public ArrayList<Item> getItems(){ return orderedItems;}

    public String getOrderID(){ return orderID;}





}
