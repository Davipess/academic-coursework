import java.util.Scanner;

void main() {
    System.out.println("Insere a tua idade:");
    Scanner agefinder = new Scanner(System.in);
    int age = agefinder.nextInt();
    int age_tens = age / 10;
    int age_singles = age % 10;

    age_tens = ((age_tens * 5) + 3) * 2;
    int agefinalized = age_tens + age_singles;
    agefinalized = agefinalized - 6;
    agefinder.close();
    System.out.println(agefinalized);
}
