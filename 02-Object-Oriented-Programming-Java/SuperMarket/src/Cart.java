import Exceptions.CapacityExceededException;
import Exceptions.ItemNotInCartException;

public interface Cart {


    void addItemToCart(Item item) throws CapacityExceededException;

    void removeItemFromCart(Item item) throws ItemNotInCartException;

    int pay();
}
