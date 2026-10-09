import java.util.Scanner;

void main() {
    Scanner age = new Scanner(System.in);

    int age1 = age.nextInt();
    int age2 = age.nextInt();
    age.nextLine();
    age.close();

    if (age1 >= age2) {
        System.out.println(age1);
    } else {
        System.out.println(age2);
    }
}