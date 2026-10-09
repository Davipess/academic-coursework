import java.util.Scanner;

public class Main {

    public static void evaluateEnding(Cart myCart) {
        ItemIterator it = myCart.iterator();

        while (it.hasNext()) {
            Item item = it.next();
            if (!item.getDescription().isEmpty()) {
                System.out.println(item.getDescription() + " " + item.getItemVal());
            }
        }
        System.out.println(myCart.getTotalCost() + " " + myCart.getCuponVal());
    }

    private static Cart createCart(Scanner shop) {
        int cuponVal = shop.nextInt();
        int numItems = shop.nextInt();
        Cart myCart = new Cart(cuponVal, numItems);

        for (int i = 0; i < numItems; i++) {
            int itemVal = shop.nextInt();
            shop.nextLine();
            String description = shop.nextLine();
            myCart.addItem(itemVal, description);
        }

        return myCart;
    }

    public static void main(String[] args) {
        Scanner shop = new Scanner(System.in);
        Cart myCart = createCart(shop);

        myCart.processPurchase();
        evaluateEnding(myCart);

        shop.close();
    }
}