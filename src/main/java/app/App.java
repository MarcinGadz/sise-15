package app;

import app.solver.ResultSet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        String algorithm, additionalInfo, input, solution, stats;
        if (args.length != 5) {
            System.out.println("Niepoprawne parametry");
            return;
        }
        algorithm = args[0];
        additionalInfo = args[1];
        input = args[2];
        solution = args[3];
        stats = args[4];

        switch (algorithm.toLowerCase()) {
            case "bfs" -> System.out.println("BFS");
            case "dfs" -> System.out.println("DFS");
            case "astr" -> System.out.println("ASTR");
        }

        //Parse file with name as in 'input'
        String filename = input;
        short[][] tab = Parser.parse(filename);

        //save result to file with name as in solution
        try {
            FileOutputStream outputStream = new FileOutputStream(solution);
            byte[] strToBytes = "PLIK Z WYNIKAMI".getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Save stats to file 'stats'
        ResultSet s = new ResultSet(
                "LDUPLLPDLDLD"
                , 230,
                150,
                24,
                1345672);
        try {
            FileOutputStream outputStream = new FileOutputStream(stats);
            byte[] strToBytes = s.generateReport().getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
