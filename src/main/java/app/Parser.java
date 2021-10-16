package app;

import java.io.*;

public class Parser {
    public static short[][] parse(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String[] content = reader.lines().toArray(String[]::new);
            String[] head = content[0].split(" ");
            short rows = Short.parseShort(head[0]);
            short cols = Short.parseShort(head[1]);
            short[][] tab = new short[rows][cols];
            for (int i = 1; i < rows + 1; i++) {
                String[] line = content[i].split(" ");
                for (int j = 0; j < cols; j++) {
                    if (i == rows && j == cols - 1) {
                        tab[i-1][j] = 0;
                        continue;
                    }
                    tab[i-1][j] = Short.parseShort(line[j]);
                }
            }
            return tab;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find specified file: " + filename);
            e.printStackTrace();
        }
        return null;
    }
}
