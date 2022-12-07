import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class Directory {
    HashMap<String, Integer> files;
    HashMap<String, Directory> subdirectories;
    Directory parent;
    int size;

    public Directory(Directory parent) {
        this();
        this.parent = parent;
    }

    public Directory() {
        this.files = new HashMap<String, Integer>();
        this.subdirectories = new HashMap<String, Directory>();
        this.size = 0;
    }
}

class Part2 {
    static Directory root = new Directory();
    static Directory currentDirectory = root;
    static int totalSize = 0;

    public static void main(String args[]) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));

        while (s.hasNextLine()) {
            String line = s.nextLine();

            if (line.startsWith("$")) {
                if (line.substring(2, 4).equals("cd")) {
                    cd(line.substring(5));
                }
            } else {
                if (line.startsWith("dir")) {
                    String directoryName = line.substring(4);
                    if (!currentDirectory.subdirectories.containsKey(directoryName)) {
                        currentDirectory.subdirectories.put(directoryName, new Directory(currentDirectory));
                    }
                } else {
                    String[] fileElements = line.split(" ");

                    int fileSize = Integer.parseInt(fileElements[0]);
                    String fileName = fileElements[1];

                    currentDirectory.files.put(fileName, fileSize);
                    currentDirectory.size += fileSize;

                    Directory temp = currentDirectory;
                    while (temp.parent != null) {
                        temp = temp.parent;
                        temp.size += fileSize;
                    }
                }
            }
        }

        System.out.println(getSizeOfSmallestDirectory(30000000, root));
    }

    public static void cd(String dirName) {
        if (dirName.equals("/")) {
            currentDirectory = root;
        } else if (dirName.equals("..")) {
            currentDirectory = currentDirectory.parent;
        } else {
            currentDirectory = currentDirectory.subdirectories.get(dirName);
        }
    }

    public static int getSizeOfSmallestDirectory(int minSize, Directory dir) {
        int minSizeToReturn = Integer.MAX_VALUE;
        for (Directory subdirectory : dir.subdirectories.values()) {
            if (70000000 - root.size + subdirectory.size > minSize) {
                minSizeToReturn = Math.min(subdirectory.size, minSizeToReturn);
            }
            minSizeToReturn = Math.min(getSizeOfSmallestDirectory(minSize, subdirectory), minSizeToReturn);
        }
        return minSizeToReturn;
    }
}
