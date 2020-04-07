import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuItem extends Inventory{

    private String menuItemId;
    private String menuItemName;
    private double price;
    private ArrayList<Item> itemIngredients;


    public MenuItem(String menuItemId, String menuItemName, double price){
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.price = price;
        this.itemIngredients = new ArrayList<Item>();
    }

    public MenuItem() {
    }


    public String getMenuID(){ return this.menuItemId; }

    public String getMenuItemName(){ return this.menuItemName; }

    public double getPrice() { return this.price;}

    public ArrayList<Item> getItemIngredients(){
        return itemIngredients;
    }

    public void setItemIngredients(ArrayList<Item> ingredients){
        this.itemIngredients=ingredients;
    }


    /**
     * Checks if all ingredients in the menu item exist in inventory
     * @return boolean (true if all ingredients are in inventory, false if not)
     */
    public boolean checkIngredientsExist(HashMap<String, Item> inventory){
        //Iterate through the menuItem's ingredients and get its name and ID
        for (int x=0; x<itemIngredients.size(); x++) {
            String id = itemIngredients.get(x).getItemID();
            String name = itemIngredients.get(x).getName();

            //Check that the ingredient item exists in the inventory, and that the ID matches up with the correct name
            if (!inventory.containsKey(id)) {
                return false;
            } else if (!inventory.get(id).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
