import java.util.Scanner;

final String hasWave = "WAVE";
final String hasNWave = "FLAT";
final int def = 5;

int count = 0;
boolean waveFound = false;

void checkTemp(int num, int med) {
    if ((med + def) < num) {
        count++;
        if (count >= 6) {
            waveFound = true;
        }
    } else {
        count = 0;
    }
}

void inp(Scanner temp) {
    int med = temp.nextInt();
    int daysNum = temp.nextInt();

    for(int i = 0; i < daysNum; i++) {
        int num = temp.nextInt();
        checkTemp(num, med);
    }
}

void run() {
    Scanner temp = new Scanner(System.in);
    if (temp.hasNextInt()) {
        inp(temp);
    }
    temp.close();

    if (waveFound) {
        System.out.println(hasWave);
    } else {
        System.out.println(hasNWave);
    }
}

void main () {
    run();
}