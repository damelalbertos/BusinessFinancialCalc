public class Item extends Inventory{


    private String itemId;
    private String itemName;
    private int itemCount;



    public Item(String itemId, int itemCount, String itemName){
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemName = itemName;


    }


    public String getName(int itemId){


        return itemName;
    }


    public int getCount(int itemId){



        return itemCount;
    }


}
