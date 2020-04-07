import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Customers extends MenuItem{

    private String customerId;
    private String fName;
    private String orderId;
    private double total;
    private static Map<String, List<String>>orderedItems;
  
  
    public Customers( String orderId, String fName) {
        this.orderId = orderId;
        this.customerId=customerId;
        this.fName = fName;
        orderedItems = new HashMap<>();
    }


    /**
     * Description:
     */
    public void order(MenuItem item){
        List<String> orderList= orderedItems.get(orderId);
        if(orderList == null){
            orderList = new ArrayList<String>();
            orderedItems.put(orderId, orderList);
        }
        orderList.add(item.getMenuItemName());
    }

    public String getCustomerName(){return fName;}

    public String getCustomerId(){ return customerId;}

    public double getTotal() { return total;}

    public String getItems(){
        for(String key : orderedItems.get(orderId)){
            //String orderString  = orderList.toString();
            String orderString = orderedItems.get(orderId).toString();
            return orderString.substring(1, orderString.length()-1); //removes brackets);

        }

        return null;
        }
  
  
    public String getOrderId(){ return orderId;}
}
