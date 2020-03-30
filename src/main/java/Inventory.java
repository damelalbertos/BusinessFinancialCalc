import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

class EmptyInventoryException extends Exception{
    public EmptyInventoryException(String errorMessage) {
        super(errorMessage);
    }
}

class ItemDoesNotExistsException extends Exception{
    public ItemDoesNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}

class ItemAlreadyExistsException extends Exception{
    public ItemAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}

public class Inventory {

    private HashMap<String, Item> inventory;


    public Inventory(){
        this.inventory = new HashMap<String, Item>();
    }

    /**
     * Description: Pass in Item id so we know which one to buy more
     * products of to increase amount of items
     */
    public int buyMoreProducts(String itemId, int amount) throws ItemDoesNotExistsException {
        if(!inventory.containsKey(itemId)){
            throw new ItemDoesNotExistsException("No Item to buy products for");
        }
        return inventory.get(itemId).addCount(amount);
    }


    /**
     * Description: Gives the chosen item based on itemId
     * @param itemId
     * @return Item object
     */
    public Item getItem(String itemId){
        Item item = inventory.get(itemId);
        return item;
    }

    /**
     * Purpose: Adds a new item to inventory HashMap
     * Parameters: the item to be added
     */
    public void addItem(Item item) throws ItemAlreadyExistsException {
        if (inventory.containsKey(item.getItemID())) {
            throw new ItemAlreadyExistsException("ID already exists");
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
    public void removeItem(String itemId) throws ItemDoesNotExistsException {
        if (!inventory.containsKey(itemId)) {
            throw new ItemDoesNotExistsException("ID doesn't exist in inventory");
        } else {
            inventory.remove(itemId);
        }

    }

    /**
     * Description: Gives the cost of a specific item
     * Parameters: the itemId of the specified item
     * returns: the cost of that item.
     */
    public double getItemCost(String itemId) throws ItemDoesNotExistsException {
        if (!inventory.containsKey(itemId)) {
            throw new ItemDoesNotExistsException("ID doesn't exist in inventory");
        } else {
            return inventory.get(itemId).getCost();
        }
    }

    /**
     * Description: Return a string with the item name and item count of all items
     * Parameters: None
     */

    public String getProductInventory() throws EmptyInventoryException {
        StringBuilder result  = new StringBuilder();
        //check if inventory is empty
        if (inventory.isEmpty()){
            throw new EmptyInventoryException("Inventory is Empty!;");
        }

        // create string
        Iterator<Map.Entry<String, Item>> it = inventory.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, Item> entry = (Map.Entry<String, Item>) it.next();
            String itemCountStr = Integer.toString(entry.getValue().getCount());
            result.append(entry.getValue().getName() + ": " + itemCountStr + "\n");
        }

        return result.toString();
    }

    /**
     * Description: Return a string with the item name and item count for a specific item
     * Parameters: String itemName
     */
    public int getItemCount(String itemId) throws ItemDoesNotExistsException {
        //check it item is in inventory, throw exception if not
        if (!inventory.containsKey(itemId)){
            throw new ItemDoesNotExistsException("Item is not inventory!");
        }

        //return ItemCount for specified item
        return inventory.get(itemId).getCount();
    }


    /**
     * Description: Returns the total amount of items in the inventory list
     * Parameters: None
     * Returns: double inventoryAmount
     */
    public int getInventoryCount(){
        //todo
        return 0;
    }

    /**
     * Description: decrement amount value for given item
     * parameters: String itemId
     */
    public void decrementItem(String itemId){
        //TODO
        //check if item exists in inventory, throw exception if not


        //decrement itemcount of specified item
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }
}

