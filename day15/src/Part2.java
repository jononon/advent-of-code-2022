import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

class Part2 {

    public static void main(String args[]) throws FileNotFoundException {

        Scanner s = new Scanner(new File("day15/src/input.txt"));

        Map<String, Integer> borderCells = new HashMap<String, Integer>();
        Set<String> sensorLocations = new HashSet<String>();

        while (s.hasNextLine()) {
            int sensorX, sensorY, beaconX, beaconY;
            String line = s.nextLine();
            sensorX = Integer.parseInt(line.substring(line.indexOf("x") + 2, line.indexOf(",")));
            sensorY = Integer.parseInt(line.substring(line.indexOf("y") + 2, line.indexOf(":")));
            beaconX = Integer.parseInt(line.substring(line.lastIndexOf("x") + 2, line.lastIndexOf(",")));
            beaconY = Integer.parseInt(line.substring(line.lastIndexOf("y") + 2));

            int radius = manhattanDistances(sensorX, beaconX, sensorY, beaconY);

            sensorLocations.add(coordinateToString(sensorX, sensorY));

            addBorderLocations(sensorX, sensorY, radius, borderCells);
        }

        int comparisonNumber = 0;
        comparisonNumber = setBit(comparisonNumber, 0);
        comparisonNumber = setBit(comparisonNumber, 1);
        comparisonNumber = setBit(comparisonNumber, 2);
        comparisonNumber = setBit(comparisonNumber, 3);

        for (String cell : borderCells.keySet()) {
            if (borderCells.get(cell) == comparisonNumber && !sensorLocations.contains(cell)) {
                String[] arr = cell.split(",");

                int x = Integer.parseInt(arr[0]);
                int y = Integer.parseInt(arr[1]);

                System.out.println(coordinateToString(x, y));

                Long output = (long) x * 4000000 + (long) y;
                System.out.println(output);
            }
        }
    }

    public static boolean isSurrounded(int x, int y, Set<String> borders) {
        if (borders.contains(coordinateToString(x, y))) {
            return false;
        }

        List<String> locations = new LinkedList<String>();
        locations.add(coordinateToString(x - 1, y));
        locations.add(coordinateToString(x + 1, y));
        locations.add(coordinateToString(x, y - 1));
        locations.add(coordinateToString(x, y + 1));

        return borders.containsAll(locations);
    }

    public static int manhattanDistances(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static String coordinateToString(int x, int y) {
        return "" + x + "," + y;
    }

    // bit 0 = UL
    // 1 = DL
    // 2 = UR
    // 3 = DR
    public static int setBit(int number, int position) {
        return number | (1 << position);
    }

    public static void addBorderLocations(int x, int y, int inputRadius, Map<String, Integer> locations) {
        int radius = inputRadius + 1;
        for (int yOffset = radius; yOffset >= -radius; yOffset--) {
            int xRadius = radius - Math.abs(yOffset);

            if (!(yOffset == 0 || yOffset == radius || yOffset == -radius)) {
                String key1 = coordinateToString(x - xRadius, y - yOffset);
                locations.putIfAbsent(key1, 0);
                if (yOffset > 0) {
                    locations.put(key1, setBit(locations.get(key1), (byte) 0));
                } else {
                    locations.put(key1, setBit(locations.get(key1), (byte) 1));
                }

                String key2 = coordinateToString(x + xRadius, y - yOffset);
                locations.putIfAbsent(key2, 0);
                if (yOffset > 0) {
                    locations.put(key2, setBit(locations.get(key2), (byte) 2));
                } else {
                    locations.put(key2, setBit(locations.get(key2), (byte) 3));
                }
            }
        }
    }
}
