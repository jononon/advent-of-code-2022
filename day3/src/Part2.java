
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

class Part2 {
    public static void main(String args[]) {

        try {

            Scanner s = new Scanner(new File("src/input.txt"));

            int priorities = 0;
            while (s.hasNextLine()) {
                String sack1, sack2, sack3;
                sack1 = s.nextLine();
                sack2 = s.nextLine();
                sack3 = s.nextLine();
                HashSet<Character> sackOneLetters = new HashSet<Character>();
                for (char character : sack1.toCharArray()) {
                    sackOneLetters.add(character);
                }
                HashSet<Character> sackTwoLetters = new HashSet<Character>();
                for (char character : sack2.toCharArray()) {
                    sackTwoLetters.add(character);
                }

                for (int i = 0; i < sack3.length(); i++) {
                    char commonLetter = sack3.charAt(i);
                    if (sackOneLetters.contains(commonLetter) &&
                            sackTwoLetters.contains(commonLetter)) {
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
