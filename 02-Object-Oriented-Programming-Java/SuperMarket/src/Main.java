import Exceptions.*;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SuperMarket sp = new SuperMarketClass();
        executeCommand(sc, sp);
        sc.close();
    }

    private static void executeCommand(Scanner sc, SuperMarket sp) {
        Messages cmd;
        do {
            cmd = Messages.valueOf(sc.next().toUpperCase());
            switch (cmd) {
                case NEW -> {
                    Messages type = Messages.valueOf(sc.next().toUpperCase());
                    if (type == Messages.CART) {
                        createNewCart(sc, sp);
                    } else if (type == Messages.ITEM) {
                        createNewItem(sc, sp);
                    }
                }
                case ADD -> addItem(sc, sp);
                case REMOVE -> removeItem(sc, sp);
                case LIST -> listItems(sc, sp);
                case PAY -> payCart(sc, sp);
                case EXIT -> setExit();
            }
        } while (cmd != Messages.EXIT);
    }

    public static void createNewCart(Scanner sc, SuperMarket sp) {
        String cartId = sc.next();
        int volume = sc.nextInt();

        try {
            sp.newCart(cartId, volume);
            System.out.println(Messages.CART_ADDED_MSG.getMessage());
        } catch (CartAlreadyExistsException e) {
            System.out.println(Messages.CART_ALREADY_EXIST_MSG.getMessage());
        }
    }

    public static void createNewItem(Scanner sc, SuperMarket sp) {
        String name = sc.next();
        int price = sc.nextInt();
        int volume = sc.nextInt();

        try {
            sp.newItem(name, price, volume);
            System.out.println(Messages.ITEM_CREATED_MSG.getMessage());
        } catch (ItemAlreadyExistsException e) {
            System.out.println(Messages.ITEM_ALREADY_EXIST.getMessage());
        }
    }

    public static void addItem(Scanner sc, SuperMarket sp) {
        String name = sc.next();
        String cartId = sc.next();

        try {
            sp.addItem(name, cartId);
            System.out.println(Messages.ITEM_ADDED_MSG.getMessage());
        } catch (NonExistingCartException e) {
            System.out.println(Messages.CART_NOT_EXIST.getMessage());
        } catch (NonExistingItemException e) {
            System.out.println(Messages.ITEM_NOT_EXIST.getMessage());
        } catch (CapacityExceededException e) {
            System.out.println(Messages.CAPACITY_EXCEEDED.getMessage());
        }
    }

    public static void removeItem(Scanner sc, SuperMarket sp) {
        String name = sc.next();
        String cartId = sc.next();

        try {
            sp.removeItem(name, cartId);
            System.out.println(Messages.ITEM_REMOVED_SUCESSFULLY.getMessage());
        } catch (NonExistingCartException e) {
            System.out.println(Messages.CART_NOT_EXIST.getMessage());
        } catch (ItemNotInCartException e) {
            System.out.println(Messages.ITEM_IS_NOT_IN_CART.getMessage());
        }
    }

    private static void listItems(Scanner sc, SuperMarket sp) {
        String cartId = sc.next();

        try {
            Iterator<Item> iterator = sp.getCartItemsIterator(cartId);

            while (iterator.hasNext()) {
                Item item = iterator.next();
                System.out.println(item.name() + " " + item.price());
            }

        } catch (NonExistingCartException e) {
            System.out.println(Messages.CART_NOT_EXIST.getMessage());
        } catch (EmptyCartException e) {
            System.out.println(Messages.EMPTY_CART.getMessage());
        }
    }

    public static void payCart(Scanner sc, SuperMarket sp) {
        String cartId = sc.next();

        try {
            int total = sp.pay(cartId);
            System.out.println(total);
        } catch (NonExistingCartException e) {
            System.out.println(Messages.CART_NOT_EXIST.getMessage());
        } catch (EmptyCartException e) {
            System.out.println(Messages.EMPTY_CART.getMessage());
        }
    }

    public static void setExit() {
        System.out.println(Messages.BYE_MSG.getMessage());
    }
}