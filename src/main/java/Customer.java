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
        //throw an exception if they order an item not on the menu or ingredients used are out of stock
        for (int m = 0; m < orderItems.size(); m++) {
            if (!CentralBusiness.menu.containsKey(orderItems.get(m).getMenuID())) {
                throw new ItemDoesNotExistsException(orderItems.get(m).getMenuItemName() + " does not exist in menu");
            }
            for (int n = 0; n < orderItems.get(m).getItemIngredients().size(); n++) {
                if (orderItems.get(m).getItemIngredients().get(n).getCount() < 1) {
                    throw new ItemCountAt0Exception("Sorry, we sre out of "+orderItems.get(m).getItemIngredients().get(n)+ ", please order again!");
                }
            }
        }

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
            //add revenue for each individual revenue for their own revenue
            CentralBusiness.setRevenueByItem(orderItems.get(x), orderItems.get(x).getPrice());
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

