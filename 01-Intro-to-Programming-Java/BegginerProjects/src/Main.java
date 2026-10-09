import java.util.Scanner;

final String GRANTED = "ACCESS GRANTED";
final String DENIED = "ACCESS DENIED";
final int LIMIT = 4;
int corr = 0;

void run() {
    if (corr == 0) {
        System.out.println(GRANTED);
    } else {
        System.out.println(DENIED);
    }
}

void inp(Scanner check) {
    for(int i = 0; i < LIMIT; i++) {
        String pass = check.next();
        if (!pass.equals("umdoistresquatro")) {
            corr++;
        }
    }
    run();
}

void main() {
    Scanner check = new Scanner(System.in);
    if (check.hasNext()) {
        inp(check);
    }
    check.close();
}