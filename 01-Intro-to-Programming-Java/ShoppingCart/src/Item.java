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
}