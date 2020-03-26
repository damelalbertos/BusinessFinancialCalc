import java.util.HashMap;

public class Order {

    private String orderID;
    private String customerID;
    private HashMap<String, Item> orderedItems;
    private double total;

    public Order(String orderID, String customerID, HashMap<String, Item> orderedItems, double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderedItems = new HashMap<String, Item>();
        this.total = total;
    }
    public String getCustomerID(){ return customerID;}

    public double getTotal() { return total;}

    public HashMap<String, Item> getItems(){ return orderedItems;}

    public String getOrderID(){ return orderID;}





}
