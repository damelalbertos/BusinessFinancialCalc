public class Item{


    private String itemId;
    private String itemName;
    private int itemCount;
    private double cost;


    public Item() {

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Item(String itemId, int itemCount, String itemName, double cost){
        this.itemId = itemId;
        this.itemName = itemName;
        if (itemCount < 0){ // checks is item count is initialized as lest than 0
            throw new IllegalArgumentException("Item Count can't be a negative number");
        }
        else{this.itemCount = itemCount;}
        if (!isAmountValid(cost)){ //validates cost of item amount
            throw new IllegalArgumentException("Cost amount is invalid!");
        }
        else{this.cost = cost;}
    }


    public String getName(){ return this.itemName; }

    public void decrementCount(){this.itemCount-=1;}

    public int getCount(){ return this.itemCount; }

    public double getCost() { return this.cost;}

    public String getItemID(){return this.itemId;}

    public void addCount(int amount){
        itemCount+=amount;
    }

    public String itemToString(){
        StringBuilder result  = new StringBuilder();
        result.append("#"+ itemId + " " + itemName +":" + itemCount + ", " + cost);
        return result.toString();
    }


    /**
     * Checks if the amount is in proper format
     * @param amount
     * @return boolean (true if amount is positive and has two decimal points or less, otherwise false)
     */
    public static boolean isAmountValid(double amount) {
        //Splits the amount to check for proper decimal places
        String[] splitter = Double.toString(amount).split("\\.");
        splitter[0].length();   // Before Decimal Count
        int decimalLength = splitter[1].length();
        if(amount > 0 && decimalLength < 3){
            return true;
        }
        else{
            return false;
        }
    }


}
