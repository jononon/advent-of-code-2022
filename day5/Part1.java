import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Part1 {
    public static void main (String args[]) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));

        ArrayList<String> lines = new ArrayList<String>();
        int numberOfColumns = -1;

        while(s.hasNextLine()) {
            String line = s.nextLine();

            if(line.charAt(1) == '1') {
                numberOfColumns = Integer.parseInt(line.substring(line.length() - 2, line.length() - 1));
                s.nextLine();
                break;
            }

            lines.add(line);
        }

        HashMap<String, Stack<Character>> dockyard = new HashMap<String, Stack<Character>>();

        for (int i = 1; i <= numberOfColumns; i++) {
            dockyard.put("" + i, new Stack<Character>());
        }

        for(int i = lines.size() - 1; i >= 0; i--) {
            String line = lines.get(i);
            for (int j = 1; j <= numberOfColumns; j++) {
                char crate = line.charAt(j * 4 - 3);
                if (crate != ' ') {
                    dockyard.get("" + j).push(crate);
                }
            }
        }

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] words = line.split(" ");
            int moves;
            String from, to;
            moves = Integer.parseInt(words[1]);
            from = words[3];
            to = words[5];

            for(int i = 0; i < moves; i++) {
                dockyard.get(to).push(dockyard.get(from).pop());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numberOfColumns; i++) {
            sb.append(dockyard.get("" + i).peek());
        }
        System.out.println(sb);

    }
}
