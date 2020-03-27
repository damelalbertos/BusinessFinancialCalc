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


    }


    /**
     * adding new item to inventory
     *
     *
     */
    public void addItem(Item item){
        //todo
    }


    /**
     * Description: Pass in Item id so we know the cost of the item we are gonna buy
     *
     *
     */
    public double getItemCost(String itemId){
        //TODO
        return 0.0;
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
