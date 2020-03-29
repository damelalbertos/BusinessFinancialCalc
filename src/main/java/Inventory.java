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
    public int buyMoreProducts(String itemId, int amount){
        //TODO

        if(!inventory.containsKey(itemId)){
            throw new IllegalArgumentException("No Item to buy products for");
        }
        return inventory.get(itemId).addCount(amount);
    }

    public Item getItem(String itemId){
        return inventory.get(itemId);
    }

    /**
     * Purpose: Adds a new item to inventory HashMap
     * Parameters: the item to be added
     */
    public void addItem(Item item) {
        if (inventory.containsKey(item.getItemID())) {
            throw new IllegalArgumentException("ID already exists");
        } else if (!Item.isAmountValid(item.getCost())) {
            throw new IllegalArgumentException("Invalid cost");
        } else {
            inventory.put(item.getItemID(), item);
        }
    }

    /**
     * Purpose: Removes an item from inventory based on itemId
     * Parameters: the id of the item to be removed
     */
    public void removeItem(String itemId){
        if (!inventory.containsKey(itemId)) {
            throw new IllegalArgumentException("ID doesn't exist in inventory");
        } else {
            inventory.remove(itemId);
        }

    }

    /**
     * Description: Gives the cost of a specific item
     * Parameters: the itemId of the specified item
     * returns: the cost of that item.
     */
    public double getItemCost(String itemId){
        if (!inventory.containsKey(itemId)) {
            throw new IllegalArgumentException("ID doesn't exist in inventory");
        } else {
            return inventory.get(itemId).getCost();
        }
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
