import Exceptions.*;

import java.util.Iterator;

public interface SuperMarket {

    void newCart(String cartId, int capacity) throws CartAlreadyExistsException;

    void newItem(String name, int price, int volume) throws ItemAlreadyExistsException;

    void addItem(String itemName, String cartId) throws NonExistingCartException, NonExistingItemException, CapacityExceededException;

    void removeItem(String itemName, String cartId) throws NonExistingCartException, ItemNotInCartException;

    Iterator<Item> getCartItemsIterator(String cartId) throws NonExistingCartException, EmptyCartException;

    int pay(String cartId) throws NonExistingCartException, EmptyCartException;
}