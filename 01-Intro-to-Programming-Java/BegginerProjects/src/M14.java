import java.util.Scanner;

final int NUMC = 5;
int numpl;
int bestScore = 0;
int winnerCount = 0;

void inp(Scanner win) {
    numpl = win.nextInt();

    for (int i = 0; i < numpl; i++) {
        int[] hand = readHand(win);
        int score = CalcScore(hand);

        if (score > bestScore) {
            bestScore = score;
            winnerCount = 1;
        } else if (score == bestScore) {
            winnerCount++;
        }
    }

    System.out.println(winnerCount + " " + bestScore);
}

int[] readHand(Scanner win) {
    int[] hand = new int[NUMC];
    for (int j = 0; j < NUMC; j++) {
        hand[j] = win.nextInt();
    }
    return hand;
}

int CalcScore(int[] hand) {
    int score = 0;
    boolean[] counted = new boolean[NUMC];

    for (int i = 0; i < NUMC; i++) {
        if (!counted[i]) {
            int count = 1;
            for (int j = i + 1; j < NUMC; j++) {
                if (hand[i] == hand[j]) {
                    count++;
                    counted[j] = true;
                }
            }

            if (count == 1) {
                score += hand[i];
            } else if (count == 2) {
                score += 3 * hand[i];
            } else if (count == 3) {
                score += 5 * hand[i];
            } else if (count == 4) {
                score += 10 * hand[i];
            }
        }
    }

    return score;
}

void main() {
    Scanner win = new Scanner(System.in);
    if (win.hasNextInt()) {
        inp(win);
    }
    win.close();
}