import java.util.Scanner;

void main() {
    Scanner ageatm = new Scanner(System.in);

    int ageR = ageatm.nextInt();
    int ageM = ageatm.nextInt();
    ageatm.nextLine();
    ageatm.close();
    int age = 0;

    while (ageR < ageM / 2.000) {
        ageR += 1;
        ageM += 1;
        age += 1;
    }
    System.out.println(age);


}

