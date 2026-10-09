import java.util.Scanner;
import java.io.*;

public class Main {

    private static final String INIC_MSG = "Total distinct words: %d\n";

    public static BookDigest processBook(Scanner file) {
        BookDigest bkd = new BookDigest();

        while (file.hasNextLine()) {
            String line = file.nextLine();
            // Split by space instead of character
            String[] wordsInLn = line.split(" ");

            for (int i = 0; i < wordsInLn.length; i++) {
                String cleanWord = wordsInLn[i].trim();
                if (!cleanWord.isEmpty()) {
                    bkd.addOrInc(cleanWord);
                }
            }
        }

        file.close();
        return bkd;
    }

    public static void processFileAndOutput(Scanner file, int inf, int sup) {
        BookDigest bkd = processBook(file);
        System.out.printf(INIC_MSG, bkd.getNmbWords());
        produceIteOut(bkd.createIter(inf, sup));
    }

    private static void produceIteOut(BookWordIterator it) {
        while (it.hasNext()) {
            WordsInBook current = it.next();
            System.out.println(current.getWord() + " - " + current.getOccurrences());
        }
    }

    public static int readIntLn(Scanner in) {
        int val = in.nextInt();
        in.nextLine(); // Clear the buffer
        return val;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String fileName = in.nextLine();
        int minNmbOccurs = in.nextInt();
        int maxNmbOccurs = readIntLn(in);

        if (0 < minNmbOccurs && minNmbOccurs <= maxNmbOccurs) {
            try {
                Scanner file = new Scanner(new FileReader(fileName));
                processFileAndOutput(file, minNmbOccurs, maxNmbOccurs);
            } catch (FileNotFoundException exception) {
                System.out.println("File Not Found");
            }
        }

        in.close();
    }
}