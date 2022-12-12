import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

class Monkey {
    Queue<BigInteger> items;
    String operation;
    int test;
    int monkeyTrueId;
    int monkeyFalseId;
    BigInteger numInspected;

    public Monkey() {
        items = new LinkedList<BigInteger>();
        numInspected = BigInteger.ZERO;
    }
}

class Part2 {
    public static void main(String args[]) throws FileNotFoundException {
        Monkey[] monkeys = new Monkey[8];
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i] = new Monkey();
        }

        Scanner s = new Scanner(new File("input.txt"));

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
                            monkeys[monkeyNumber].items.add(new BigInteger(item));
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

        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                System.out.println(i);
            }
            for (int j = 0; j < monkeys.length; j++) {
                while (!monkeys[j].items.isEmpty()) {
                    BigInteger val = monkeys[j].items.poll();
                    monkeys[j].numInspected = monkeys[j].numInspected.add(BigInteger.ONE);
                    BigInteger newVal = doMath(monkeys[j].operation, val);
                    if (newVal.mod(new BigInteger("" + monkeys[j].test)).equals(BigInteger.ZERO)) {
                        monkeys[monkeys[j].monkeyTrueId].items.add(newVal);
                    } else {
                        monkeys[monkeys[j].monkeyFalseId].items.add(newVal);
                    }
                }
            }
        }

        ArrayList<BigInteger> business = new ArrayList<BigInteger>();
        for (Monkey monkey : monkeys) {
            business.add(monkey.numInspected);
        }
        Collections.sort(business);
        BigInteger largestMonkey = business.get(business.size() - 1);
        BigInteger secondLargestMonkey = business.get(business.size() - 2);
        System.out.println(largestMonkey.multiply(secondLargestMonkey));
    }

    public static BigInteger doMath(String function, BigInteger val) {
        BigInteger retVal = BigInteger.ZERO;
        if (function.contains("*")) {
            String[] split = function.split(" * ");
            if (split[2].equals("old")) {
                retVal = val.multiply(val);
            } else {
                retVal = val.multiply(new BigInteger(split[2]));
            }
        } else { // contains +
            String[] split = function.split(" ");
            if (split[2].equals("old")) {
                retVal = val.add(val);
            } else {
                retVal = val.add(new BigInteger(split[2]));
            }
        }
        return retVal;
    }
}
