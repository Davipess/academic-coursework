import java.util.Scanner;

final String noP = "No free lunch";
int found = 0;

void checkNums(int num, int fixed) {
    if (num <= fixed && num > found) {
        found = num;
    }
}

void inp(Scanner lunch) {
    int fixed = lunch.nextInt();
    int numFriends = lunch.nextInt();

    for (int i = 0; i < numFriends; i++) {
        int num = lunch.nextInt();
        checkNums(num, fixed);
    }
}

void run() {
    Scanner lunch = new Scanner(System.in);
    inp(lunch);
    lunch.nextLine();
    lunch.close();

    if (found != 0) {
        System.out.println(found);
    } else {
        System.out.println(noP);
    }
}

void main() {
    run();
}

