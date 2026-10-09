import java.util.Scanner;

int combinations(int n, int k) {
    int result = 0;

    if (k == 0 || k == n) {
        result = 1;
    } else if (k <= n) {
        result = combinations(n - 1, k - 1) + combinations(n - 1, k);
    }

    return result;
}

void inp(Scanner comb) {
    int n = comb.nextInt();
    int k = comb.nextInt();

    int result = combinations(n, k);
    System.out.println(result);
}

void main() {
    Scanner comb = new Scanner(System.in);
    if (comb.hasNextInt()) {
        inp(comb);
    }
    comb.close();
}