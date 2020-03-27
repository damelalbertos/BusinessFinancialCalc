import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;



public class Inventory {

    private HashMap<String, Item> inventory;

    public Inventory(){
        this.inventory = new HashMap<String, Item>();
    }

    /**
     * Description: Pass in Item id so we know which one to buy more
     * products of to increase amount of items
     */
    public void buyMoreProducts(){
        //TODO
    }


    /**
     * Purpose: Adds a new item to inventory HashMap
     * Parameters: the item to be added
     */
    public void addItem(Item item){
        inventory.put(item.getItemID(), item);
    }

    /**
     * Purpose: Removes an item from inventory based on itemId
     * Parameters: the id of the item to be removed
     */
    public void removeItem(String itemId){
        //todo
    }

    /**
     * Description: Gives the cost of a specific item
     * Parameters: the itemId of the specified item
     * returns: the cost of that item.
     */
    public double getItemCost(String itemId){
        Item item = inventory.get(itemId);
        double itemCost = item.getCost();
        return itemCost;
    }

    /**
     * Return all item names with the amount each item has in a string
     */

    public String getProductInventory(){
        //todo
        return "";
    }


    /**
     * returns the amount of items in the inventory list
     */
    public double getInventoryCount(){
        //todo
        return 0.0;
    }

    /**
     *(take item id and decrease based on customers order?)
     */
    public void decrementItem(){


    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }
}
