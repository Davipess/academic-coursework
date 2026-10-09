import Exceptions.*;

import java.util.HashMap;
import java.util.Iterator;

public class SuperMarketClass implements SuperMarket {

    private final HashMap<String, Cart> cart;
    private final HashMap<String, Item> items;

    public SuperMarketClass() {
        this.cart = new HashMap<>();
        this.items = new HashMap<>();
    }

    @Override
    public void newCart(String cartId, int capacity) throws CartAlreadyExistsException {
        if (cart.containsKey(cartId)) {
            throw new CartAlreadyExistsException();
        }
        Cart c = new CartClass(cartId, capacity);
        cart.put(cartId, c);
    }

    @Override
    public void newItem(String name, int price, int volume) throws ItemAlreadyExistsException {
        if (items.containsKey(name)) {
            throw new ItemAlreadyExistsException();
        }
        Item i = new ItemClass(name, price, volume);
        items.put(name, i);
    }

    @Override
    public void addItem(String itemName, String cartId) throws NonExistingCartException, NonExistingItemException, CapacityExceededException {
        Cart c = cart.get(cartId);
        if (c == null) {
            throw new NonExistingCartException();
        }

        Item i = items.get(itemName);
        if (i == null) {
            throw new NonExistingItemException();
        }

        c.addItemToCart(i);
    }

    @Override
    public void removeItem(String itemName, String cartId) throws NonExistingCartException, ItemNotInCartException {
        Cart c = cart.get(cartId);
        if (c == null) {
            throw new NonExistingCartException();
        }

        Item i = items.get(itemName);
        if (i == null) {
            throw new ItemNotInCartException();
        }

        c.removeItemFromCart(i);
    }

    @Override
    public Iterator<Item> getCartItemsIterator(String cartId) throws NonExistingCartException, EmptyCartException {
        Cart c = cart.get(cartId);
        if (c == null) {
            throw new NonExistingCartException();
        }

        if (c instanceof CartClass) {
            Iterator<Item> iterator = ((CartClass) c).iterator();

            if (!iterator.hasNext()) {
                throw new EmptyCartException();
            }
            return iterator;
        }

        return null;
    }

    @Override
    public int pay(String cartId) throws NonExistingCartException, EmptyCartException {
        Cart c = cart.get(cartId);
        if (c == null) {
            throw new NonExistingCartException();
        }

        if (c instanceof CartClass) {
            Iterator<Item> iterator = ((CartClass) c).iterator();
            if (!iterator.hasNext()) {
                throw new EmptyCartException();
            }
        }

        return c.pay();
    }
}