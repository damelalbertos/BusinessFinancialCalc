import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Customer{


    private String customerId;
    private String fName;
    private String orderId;
    private double total;
    private static Map<String, List<String>> orderedItems;
    private static Map<String, List<Double>> orderedTotal;


    public Customer(String orderId, String fName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.fName = fName;
        orderedItems = new HashMap<>();
        orderedTotal = new HashMap<>();


    }


    /**
     * Description:
     */
    public void order(MenuItem item) throws ItemAlreadyExistsException, ItemDoesNotExistsException, ItemCountAt0Exception{
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

        //get ingredients in ordered menu item
        ArrayList<Item> ingredients;
        ingredients = item.getItemIngredients();

        //decrement each ingredient used in menu item
        for (int x = 0; x<ingredients.size(); x++) {
            Inventory.decrementItem(ingredients.get(x).getItemID());
        }
        ;

        //make sure the order gets added to revenue
        CentralBusiness.addToRevenue(item.getPrice());
    }

    public String getCustomerName() {
        return fName;
    }

    public String getCustomerId() {
        return customerId;
    }


    public String getItems() {
        for (String key : orderedItems.get(orderId)) {
            String orderString = orderedItems.get(orderId).toString();
            return orderString.substring(1, orderString.length() - 1); //removes brackets);

        }
        return null;
    }


    public double getTotal() {
        for (int i = 0; i < orderedTotal.get(orderId).size(); i++) {
            total += orderedTotal.get(orderId).get(i);

        }


        //revenue += total;

        return total;
    }

    public String getOrderId() {
        return orderId;
    }




}