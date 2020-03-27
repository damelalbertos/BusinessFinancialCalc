public class Item extends Inventory{


    private String itemId;
    private String itemName;
    private int itemCount;
    private double cost;


    public Item(String itemId, int itemCount, String itemName, double cost){
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.cost = cost;


    }


    public String getName(){ return this.itemName; }


    public int getCount(){ return this.itemCount; }

    public double getCost() { return this.cost;}

    public String getItemID(){return this.itemId;}


}
