import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Customers extends MenuItem {

    private String customerId;
    private String fName;
    private String orderId;
    private double total;
    private static Map<String, List<String>> orderedItems;
    private static Map<String, List<Double>> orderedTotal;


    public Customers(String orderId, String fName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.fName = fName;
        orderedItems = new HashMap<>();
        orderedTotal = new HashMap<>();
    }


    /**
     * Description:
     */
    public void order(MenuItem item) {
        List<String> orderList = orderedItems.get(orderId);
        List<Double> totalList = orderedTotal.get(orderId);
        if (orderList == null || totalList == null) {
            orderList = new ArrayList<String>();
            totalList = new ArrayList<Double>();
            orderedItems.put(orderId, orderList);
            orderedTotal.put(orderId, totalList);

        }
        orderList.add(item.getMenuItemName());
        totalList.add(item.getPrice());


    }

    public String getCustomerName() {
        return fName;
    }

    public String getCustomerId() {
        return customerId;
    }


    public String getItems() {
        for (String key : orderedItems.get(orderId)) {
            //String orderString  = orderList.toString();
            String orderString = orderedItems.get(orderId).toString();
            String totalString = orderedTotal.get(orderId).toString();
            return orderString.substring(1, orderString.length() - 1); //removes brackets);

        }

        return null;
    }


    public double getTotal() {

        double [] arr = new double[orderedTotal.get(orderId).size()];
        for (int i = 0; i < orderedTotal.get(orderId).size(); i++) {
            String totalString = orderedTotal.get(orderId).toString().replace(",","").replace("[", "").replace("]", "");
            //key = Double.valueOf(totalString);
            arr[i]= Double.parseDouble(String.valueOf(totalString));
            total += arr[i];



        }




        return total;
    }

    public String getOrderId() {
        return orderId;
    }

}