public class Cart {

    private Item[] items;
    private int cuponVal;
    private int counter;
    private int totalCost;

    public Cart(int cuponVal, int numItems) {
        this.cuponVal = cuponVal;
        this.items = new Item[numItems];
        this.counter = 0;
        this.totalCost = 0;
    }

    public ItemIterator iterator() {
        return new ItemIterator(items, counter);
    }

    public void addItem(int itemVal, String description) {
        if (counter < items.length) {
            items[counter++] = new Item(itemVal, description);
        }
    }

    public void processPurchase() {
        for (int i = 0; i < counter; i++) {
            int itemVal = items[i].getItemVal();

            if (itemVal <= cuponVal) {
                cuponVal -= itemVal;
                totalCost += itemVal;
            } else {
                items[i].removeItem();
            }
        }
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getCuponVal() {
        return cuponVal;
    }
}