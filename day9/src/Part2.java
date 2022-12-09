import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

class Knot {
    int[] location;
    HashSet<String> locationsVisited;
    Knot head;

    public Knot() {
        location = new int[] { 0, 0 };
        locationsVisited = new HashSet<String>();
        locationsVisited.add("0,0");
    }

    public Knot(Knot head) {
        this();
        this.head = head;
    }
}

class Part2 {

    public static void main(String args[]) throws FileNotFoundException {
        Knot[] knots = new Knot[10];
        knots[0] = new Knot();
        for (int i = 1; i < 10; i++) {
            knots[i] = new Knot(knots[i - 1]);
        }

        Scanner s = new Scanner(new File("day9/src/input.txt"));

        while (s.hasNextLine()) {
            String[] instruction = s.nextLine().split(" ");

            for (int i = 0; i < Integer.parseInt(instruction[1]); i++) {
                switch (instruction[0]) {
                    case "R":
                        knots[0].location[0]++;
                        break;
                    case "L":
                        knots[0].location[0]--;
                        break;
                    case "U":
                        knots[0].location[1]++;
                        break;
                    case "D":
                        knots[0].location[1]--;
                        break;
                }
                for (int j = 0; j < knots.length - 1; j++) {
                    moveTailToValidSpot(knots[j], knots[j + 1]);
                }
            }
        }

        System.out.println(knots[9].locationsVisited.size());
    }

    public static void moveTailToValidSpot(Knot head, Knot tail) {
        int headX = head.location[0];
        int headY = head.location[1];
        int tailX = tail.location[0];
        int tailY = tail.location[1];

        if (Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1) {
            return;
        }

        if (tail.location[0] == head.location[0]) {
            tail.location[1] += Integer.signum(head.location[1] - tail.location[1]);
        } else if (tail.location[1] == head.location[1]) {
            tail.location[0] += Integer.signum(head.location[0] - tail.location[0]);
        } else {
            tail.location[0] += Integer.signum(head.location[0] - tail.location[0]);
            tail.location[1] += Integer.signum(head.location[1] - tail.location[1]);
        }

        tail.locationsVisited.add("" + tail.location[0] + "," + tail.location[1]);
    }
}
