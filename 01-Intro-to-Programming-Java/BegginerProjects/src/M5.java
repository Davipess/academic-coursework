import java.util.Scanner;


void main() {

    Scanner el = new Scanner(System.in);
    int x1 = el.nextInt();
    int y1 = el.nextInt();
    int x2 = el.nextInt();
    int y2 = el.nextInt();
    el.nextLine();
    el.close();


    int sf = Math.abs((x1 - x2)) + Math.abs((y1 - y2));

    System.out.println(sf);
}