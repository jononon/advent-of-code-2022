import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

class Monkey {
    Queue<Integer> items;
    String operation;
    int test;
    int monkeyTrueId;
    int monkeyFalseId;
    int numInspected;

    public Monkey() {
        items = new LinkedList<Integer>();
    }
}

class Part1 {
    public static void main(String args[]) throws FileNotFoundException {
        Monkey[] monkeys = new Monkey[8];
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i] = new Monkey();
        }

        Scanner s = new Scanner(new File("test.txt"));

        int monkeyNumber = -1;
        while (s.hasNextLine()) {
            for (int i = 0; i < 7; i++) {
                String line = s.hasNextLine() ? s.nextLine() : null;
                switch (i) {
                    case 0:
                        monkeyNumber = Integer.parseInt(line.substring(line.length() - 2, line.length() - 1));
                        break;
                    case 1:
                        String startingItems = line.substring(line.indexOf(":") + 2);
                        String[] startingItemsArr = startingItems.split(", ");
                        for (String item : startingItemsArr) {
                            monkeys[monkeyNumber].items.add(Integer.parseInt(item));
                        }
                        break;
                    case 2:
                        monkeys[monkeyNumber].operation = line.substring(line.indexOf("=") + 2);
                        break;
                    case 3:
                        String[] split = line.split(" ");
                        monkeys[monkeyNumber].test = Integer.parseInt(split[split.length - 1]);
                        break;
                    case 4:
                        monkeys[monkeyNumber].monkeyTrueId = Integer.parseInt(line.substring(line.length() - 1));
                        break;
                    case 5:
                        monkeys[monkeyNumber].monkeyFalseId = Integer.parseInt(line.substring(line.length() - 1));
                        break;
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < monkeys.length; j++) {
                while (!monkeys[j].items.isEmpty()) {
                    int val = monkeys[j].items.poll();
                    monkeys[j].numInspected++;
                    int newVal = doMath(monkeys[j].operation, val);
                    if (newVal % monkeys[j].test == 0) {
                        monkeys[monkeys[j].monkeyTrueId].items.add(newVal);
                    } else {
                        monkeys[monkeys[j].monkeyFalseId].items.add(newVal);
                    }
                }
            }
        }

        for (Monkey monkey : monkeys) {
            System.out.println(monkey.numInspected);
        }
    }

    public static int doMath(String function, int val) {
        int retVal = 0;
        if (function.contains("*")) {
            String[] split = function.split(" * ");
            if (split[2].equals("old")) {
                retVal = val * val;
            } else {
                retVal = val * Integer.parseInt(split[2]);
            }
        } else { // contains +
            String[] split = function.split(" ");
            if (split[2].equals("old")) {
                retVal = val + val;
            } else {
                retVal = val + Integer.parseInt(split[2]);
            }
        }
        // System.out.println(function + " " + val + " " + retVal);
        return retVal / 3;
    }
}
