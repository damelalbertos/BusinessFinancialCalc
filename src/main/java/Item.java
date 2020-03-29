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

    /**
     * Checks if the amount is in proper format
     * @param amount
     * @return boolean (true if amount is positive and has two decimal points or less, otherwise false)
     */
    public static boolean isAmountValid(double amount) {
        String[] splitter = Double.toString(amount).split("\\.");
        splitter[0].length();   // Before Decimal Count
        int decimalLength = splitter[1].length();
        if(amount > -1 && decimalLength < 3){
            return true;
        }
        else{
            return false;
        }
    }


}
