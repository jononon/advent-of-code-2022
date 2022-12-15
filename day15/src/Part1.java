import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Part1 {

    public static void main(String args[]) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day15/src/input.txt"));

        Set<String> illegalLocations = new HashSet<String>();

        while (s.hasNextLine()) {
            int sensorX, sensorY, beaconX, beaconY;
            String line = s.nextLine();
            sensorX = Integer.parseInt(line.substring(line.indexOf("x") + 2, line.indexOf(",")));
            sensorY = Integer.parseInt(line.substring(line.indexOf("y") + 2, line.indexOf(":")));
            beaconX = Integer.parseInt(line.substring(line.lastIndexOf("x") + 2, line.lastIndexOf(",")));
            beaconY = Integer.parseInt(line.substring(line.lastIndexOf("y") + 2));

            // System.out.println(sensorX + ", " + sensorY);
            // System.out.println(beaconX + ", " + beaconY);

            // illegalLocations.add(coordinateToString(sensorX, sensorY));
            // illegalLocations.add(coordinateToString(beaconX, beaconY));

            int radius = manhattanDistances(sensorX, beaconX, sensorY, beaconY);

            illegalLocations.addAll(generateImpossibleLocations2(sensorX, sensorY, radius));

        }
        System.out.println(illegalLocations.size());
    }

    public static int manhattanDistances(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static String coordinateToString(int x, int y) {
        return "" + x + "," + y;
    }

    public static List<String> generateImpossibleLocations(int x, int y, int radius) {

        List<String> locations = new LinkedList<String>();

        for (int yOffset = radius; yOffset >= -radius; yOffset--) {
            for (int xOffset = radius - yOffset; xOffset >= -(radius - yOffset); xOffset--) {

                if (y - yOffset == 2000000) {
                    locations.add(coordinateToString(x - xOffset, y - yOffset));
                }
            }
        }
        return locations;
    }

    public static List<String> generateImpossibleLocations2(int x, int y, int radius) {
        List<String> locations = new LinkedList<String>();

        int howWideToGo = radius - Math.abs(2000000 - y);
        if (howWideToGo >= 0) {
            for (int xOffset = x - howWideToGo; xOffset < x + howWideToGo; xOffset++) {
                locations.add(coordinateToString(xOffset, 2000000));
            }
        }

        return locations;

    }
}
