import java.util.Scanner;

void findOlder(int age3, int age4) {
    if (age3 >= age4){
        System.out.println(age3);
    }
    else {
        System.out.println(age4);
    }
}

void inp(Scanner old) {
int age1 = old.nextInt();
int age2 = old.nextInt();

findOlder(age1,age2);
}

void main() {
Scanner old = new Scanner(System.in);
inp(old);
}