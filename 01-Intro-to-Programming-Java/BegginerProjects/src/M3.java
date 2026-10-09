import java.util.Scanner;

void main() {
    Scanner tl = new Scanner(System.in);

    // Input Reading
    int length = tl.nextInt();
    int width = tl.nextInt();
    int BlTlSize = tl.nextInt();
    int GrTlSize = tl.nextInt();
    tl.nextLine();

    tl.close();

    // Calculations
    int BlTlAmountL = (length + GrTlSize) / (BlTlSize + GrTlSize);
    int BlTlAmountW = (width + GrTlSize) / (BlTlSize + GrTlSize);
    int BlTlFinal = BlTlAmountL * BlTlAmountW;

    int SmallTlsTT = ((BlTlAmountL - 1) * (width / GrTlSize)) + ((BlTlAmountW - 1) * (length / GrTlSize)) - ((BlTlAmountL - 1) * (BlTlAmountW - 1));

    int WhTlAmount = (BlTlAmountL - 1) * (BlTlAmountW - 1);

    int GrTlAmount = SmallTlsTT - WhTlAmount;

    // Output
    System.out.println(BlTlFinal);
    System.out.println(GrTlAmount);
    System.out.println(WhTlAmount);
}

