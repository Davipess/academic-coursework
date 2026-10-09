import java.util.Scanner;

final int UBERXT = 10;
final int UBERXK = 80;
final int UBERX_BASE_TAR = 100;
final int MIN_TAX_UBERX = 250;

final int UBERXLT = 15;
final int UBERXLK = 120;
final int UBERXL_BASE_TAR = 150;
final int MIN_TAX_UBERXL = 350;

void CalcPrice(int serv, int tcons, int dis) {
    int price;
    if (serv == 1) { // UberX
        price = tcons * UBERXT + dis * UBERXK + UBERX_BASE_TAR;
        if (price < MIN_TAX_UBERX) {
            price = MIN_TAX_UBERX;
        }
    } else { // UberXL
        price = tcons * UBERXLT + dis * UBERXLK + UBERXL_BASE_TAR;
        if (price < MIN_TAX_UBERXL) {
            price = MIN_TAX_UBERXL;
        }
    }
    System.out.println(price);
}

void EXScenario(Scanner ub, int number) {
    int serv = ub.nextInt();
    int tcons = ub.nextInt();
    int dis = ub.nextInt();

    CalcPrice(serv, tcons, dis);
}

void main() {
    Scanner ub = new Scanner(System.in);

    EXScenario(ub, 1);
    EXScenario(ub, 2);
    EXScenario(ub, 3);

    ub.close();
}
