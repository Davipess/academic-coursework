import java.util.Scanner;

final String HAL = "Com alibi";
final String HNAL = "Sem alibi";

void HasAL(int beginC, int endC, int beginDiffP, int endDiffP) {
    if (beginC >= beginDiffP && endC <= endDiffP) {
        System.out.println(HAL);
    } else {
        System.out.println(HNAL);
    }
}

void Scan(Scanner cri, int number) {
    int beginC = cri.nextInt();
    int endC = cri.nextInt();
    int beginDiffP = cri.nextInt();
    int endDiffP = cri.nextInt();

    HasAL(beginC, endC, beginDiffP, endDiffP);
}


void main() {
    Scanner cri = new Scanner(System.in);
    Scan(cri, 1);
    Scan(cri, 2);
    Scan(cri, 3);
    Scan(cri, 4);
    cri.nextLine();
    cri.close();
}