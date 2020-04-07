import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customers extends MenuItem{

    private String customerId;
    private String fName;


    private String orderId;
    //private ArrayList<Item> orderedItems;
    private double total;

    private static List<String> orderList= new ArrayList<String>();

    private static Map<String, MenuItem>orderedItems;





    public Customers( String orderId, String fName) {
        this.orderId = orderId;
        this.customerId=customerId;
        this.fName = fName;
        orderedItems = new HashMap<>();
        List<MenuItem> orderList= new ArrayList<MenuItem>();

    }


    /**
     * Description:
     */
    public void order(MenuItem item){
        orderedItems.put(orderId, item);




    }



    public String getCustomerName(){return fName;}


    public String getCustomerId(){ return customerId;}



    public double getTotal() { return total;}



    public String getItems(){

//        for(orderedItems.Entry<String,List<String>>entry : orderedItems.entrySet() ){
//            String key = entry.getKey();

        for(String key : orderedItems.keySet()){
            orderList.add(orderedItems.get(orderId).getMenuItemName());

        }

        for(int i =0; i < orderList.size(); i++){
            return orderList.get(i);

        }

    return null;
    }

    public String getOrderId(){ return orderId;}







}
