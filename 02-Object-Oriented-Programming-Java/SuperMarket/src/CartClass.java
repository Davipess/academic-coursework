import Exceptions.CapacityExceededException;
import Exceptions.ItemNotInCartException;

import java.util.Iterator;
import java.util.LinkedList;

public class CartClass implements Cart {

    private final String cartId;
    private final int capacity;
    private final LinkedList<Item> items;
    private int currentCapacity;

    public CartClass(String cartId, int capacity) {
        this.cartId = cartId;
        this.capacity = capacity;
        this.items = new LinkedList<>();
        this.currentCapacity = 0;
    }

    @Override
    public void addItemToCart(Item item) throws CapacityExceededException {
        if (currentCapacity + item.volume() > this.capacity) {
            throw new CapacityExceededException();
        }
        this.items.add(item);
        this.currentCapacity += item.volume();
    }

    @Override
    public void removeItemFromCart(Item item) throws ItemNotInCartException {
        if (items.remove(item)) {
            currentCapacity -= item.volume();
        } else {
            throw new ItemNotInCartException();
        }
    }

    @Override
    public int pay() {
        int sum = 0;
        for (Item item : items) {
            sum += item.price();
        }
        items.clear();
        currentCapacity = 0;
        return sum;
    }

    public Iterator<Item> iterator() {
        return this.items.iterator();
    }
}