import java.util.Scanner;

boolean open = false;
int count = 0;

void checkGav(String symb) {
    if (symb.equals("#") && !open) {
        count++;
        open = false;
    } else if (symb.equals("o")) {
        open = true;
    } else if (open && symb.equals("#")) {
        open = false;
    }
}

void inp(Scanner gav) {
    int numGav = gav.nextInt();
    gav.nextLine();

    for (int i = 0; i < numGav; i++) {
        String symb = gav.nextLine();
        checkGav(symb);
    }
}

void main() {
    Scanner gav = new Scanner(System.in);
    if (gav.hasNextInt()) {
        inp(gav);
        System.out.println(count);
    }
    gav.close();
}