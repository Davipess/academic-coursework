public enum Messages {

    CART_ALREADY_EXIST_MSG("Cart already exists!"),
    CART_ADDED_MSG("Cart created successfully."),
    ITEM_ADDED_MSG("Item added successfully."),
    ITEM_CREATED_MSG("Item created successfully."),
    ITEM_ALREADY_EXIST("Item already exists!"),
    ITEM_NOT_EXIST("Non-existing item!"),
    CART_NOT_EXIST("Non-existing cart!"),
    CAPACITY_EXCEEDED("Capacity exceeded!"),
    ITEM_REMOVED_SUCESSFULLY("Item successfully removed."),
    ITEM_IS_NOT_IN_CART("Item is not in cart!"),
    EMPTY_CART("Empty cart!"),
    BYE_MSG("Bye!"),

    NEW("NEW"),
    CART("CART"),
    ITEM("ITEM"),
    ADD("ADD"),
    REMOVE("REMOVE"),
    LIST("LIST"),
    PAY("PAY"),
    EXIT("EXIT");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}