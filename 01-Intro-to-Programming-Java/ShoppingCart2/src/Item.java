public class Item {

    private int itemVal;
    private String description;

    public Item(int itemVal, String description) {
        this.itemVal = itemVal;
        this.description = description;
    }

    public int getItemVal() {
        return itemVal;
    }

    public String getDescription() {
        return description;
    }

    public void removeItem() {
        description = "";
    }

    // Fixed compareTo logic to handle negative differences properly
    public int compareTo(Item other) {
        int result = 0;
        int priceDiff = this.itemVal - other.getItemVal();

        if (priceDiff != 0) {
            result = priceDiff;
        } else {
            result = this.description.compareTo(other.getDescription());
        }

        return result;
    }
}