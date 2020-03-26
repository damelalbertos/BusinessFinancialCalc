public class Item extends Inventory{


    private String itemId;
    private String itemName;
    private int itemCount;
    private double menuPrice;


    public Item(String itemId, int itemCount, String itemName, double menuPrice){
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.menuPrice = menuPrice;


    }


    public String getName(){ return itemName; }


    public int getCount(){ return itemCount; }

    public double getMenuPrice() { return menuPrice;}

    public String getItemID(){return itemId;}


}
