
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

class Part1 {
    public static void main(String args[]) {

        try {

            Scanner s = new Scanner(new File("src/input.txt"));

            int priorities = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();

                String sack1, sack2;
                sack1 = line.substring(0, line.length() / 2);
                sack2 = line.substring(line.length() / 2);
                HashSet<Character> seenLetters = new HashSet<Character>();
                for (char character : sack1.toCharArray()) {
                    seenLetters.add(character);
                }

                for (int i = 0; i < sack2.length(); i++) {
                    char commonLetter = sack2.charAt(i);
                    if (seenLetters.contains(sack2.charAt(i))) {
                        if (commonLetter >= 'A' && commonLetter <= 'Z') {
                            priorities += commonLetter - 'A' + 27;
                        } else {
                            priorities += commonLetter - 'a' + 1;
                        }
                        break;
                    }
                }

            }
            System.out.println(priorities);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
