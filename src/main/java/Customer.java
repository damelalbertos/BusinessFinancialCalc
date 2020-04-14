import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Customer {


    private String fName;
    private String lName;

    public Customer(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public void order(ArrayList<MenuItem> orderItems, String customerID, String orderID) throws ItemDoesNotExistsException, ItemCountAt0Exception {
        //create new order
        Order newOrder = new Order(customerID, orderID, 0);

        //set ordered items to array of customer's menu items they want
        newOrder.setOrderedItems(orderItems);

        //create list for ingredients
        ArrayList<Item> ingredients = new ArrayList<>();

        //create variable that is total of order
        double tot = 0;

        for (int x = 0; x < orderItems.size(); x++) {
            //iterate through and calculate total price of all menu items
            tot+=orderItems.get(x).getPrice();
            //iterate through and add all the ingredients for the menu items to the array
            ingredients.addAll(orderItems.get(x).getItemIngredients());
        }

        //set total
        newOrder.setTotal(tot);

        //put new order in map of all orders
        CentralBusiness.allOrders.put(orderID, newOrder);

        //decrement each ingredient used in menu items in order
        for (int y = 0; y<ingredients.size(); y++) {
            CentralBusiness.inventory.decrementItem(ingredients.get(y).getItemID());
        }

        //make sure the order gets added to revenue
        CentralBusiness.addToRevenue(tot);
    }

}

