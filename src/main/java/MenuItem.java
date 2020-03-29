import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuItem {

    private String menuItemId;
    private String menuItemName;
    private double price;
    private ArrayList<Item> itemIngredients;


    public MenuItem(String menuItemId, String menuItemName, double price, ArrayList<Item> itemIngredients){
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.price = price;
        this.itemIngredients = itemIngredients;
    }


    public String getMenuID(){ return this.menuItemId; }

    public String getMenuItemName(){ return this.menuItemName; }

    public double getPrice() { return this.price;}

    public ArrayList<Item> getItemIngredients(){return this.itemIngredients;}


    /**
     * Checks if all ingredients in the menu item exist in inventory
     * @return boolean (true if all ingredients are in inventory, false if not)
     */
    public boolean checkIngredientsExist(){
        return false;
    }
}
