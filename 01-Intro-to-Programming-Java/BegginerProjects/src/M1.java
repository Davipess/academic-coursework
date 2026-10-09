import java.util.Scanner;

void main() {
    System.out.println("Escreve o teu email, primeiro nome e idade");
    Scanner agefinder = new Scanner(System.in);
    agefinder.next();
    agefinder.next();
    int age = agefinder.nextInt();
    System.out.println(age);

}