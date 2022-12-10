import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

class Part1 {

    static int[] headLocation = new int[2];
    static int[] tailLocation = new int[2];
    static HashSet<String> locationsVisited = new HashSet<String>();

    public static void main(String args[]) throws FileNotFoundException {

        Scanner s = new Scanner(new File("day9/src/input.txt"));
        locationsVisited.add("0,0");

        while (s.hasNextLine()) {
            String[] instruction = s.nextLine().split(" ");

            for (int i = 0; i < Integer.parseInt(instruction[1]); i++) {
                switch (instruction[0]) {
                    case "R":
                        headLocation[0]++;
                        break;
                    case "L":
                        headLocation[0]--;
                        break;
                    case "U":
                        headLocation[1]++;
                        break;
                    case "D":
                        headLocation[1]--;
                        break;
                }
                moveTailToValidSpot(instruction[0]);
            }
        }

        System.out.println(locationsVisited.size());
    }

    public static void moveTailToValidSpot(String dir) {
        int headX = headLocation[0];
        int headY = headLocation[1];
        int tailX = tailLocation[0];
        int tailY = tailLocation[1];

        if (Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1) {
            return;
        }

        switch (dir) {
            case "R":
                tailLocation[0] = headX - 1;
                tailLocation[1] = headY;
                break;
            case "L":
                tailLocation[0] = headX + 1;
                tailLocation[1] = headY;
                break;
            case "U":
                tailLocation[0] = headX;
                tailLocation[1] = headY - 1;
                break;
            case "D":
                tailLocation[0] = headX;
                tailLocation[1] = headY + 1;
                break;
        }

        locationsVisited.add("" + tailLocation[0] + "," + tailLocation[1]);
    }
}
