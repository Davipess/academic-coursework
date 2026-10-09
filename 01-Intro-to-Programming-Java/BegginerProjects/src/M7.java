import java.util.Scanner;

void main() {
    Scanner enc = new Scanner(System.in);
final int H_MINS = 60;
int AlexH1E = enc.nextInt();
int AlexM1E = enc.nextInt();
int AlexH1L = enc.nextInt();
int AlexM1L = enc.nextInt();
int BiaH1 = enc.nextInt();
int BiaM1 = enc.nextInt();
int AlexH2E = enc.nextInt();
int AlexM2E = enc.nextInt();
int AlexH2L = enc.nextInt();
int AlexM2L = enc.nextInt();
int BiaH2 = enc.nextInt();
int BiaM2 = enc.nextInt();
enc.nextLine();
enc.close();
int AlexEF1 = AlexH1E * H_MINS + AlexM1E;
int AlexEF2 = AlexH2E * H_MINS + AlexM2E;
int AlexLF1 = AlexH1L * H_MINS + AlexM1L;
int AlexLF2 = AlexH2L * H_MINS + AlexM2L;
int BiaF1 = BiaH1 * H_MINS + BiaM1;
int BiaF2 = BiaH2 * H_MINS + BiaM2;



if(AlexEF1 <= BiaF1 && BiaF1 <= AlexLF1) {
    System.out.println("Encontram-se");
}
else {
    System.out.println("Desencontram-se");
}
    if(AlexEF2 <= BiaF2 && BiaF2 <= AlexLF2) {
        System.out.println("Encontram-se");
    }
    else {
        System.out.println("Desencontram-se");
    }
}