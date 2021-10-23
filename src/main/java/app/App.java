package app;

//import app.solver.DfsOtherSolver;
import app.solver.AcrossSolver;
import app.solver.DfsSolver;
import app.solver.ResultSet;
import app.solver.Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
//        String algorithm, additionalInfo, input, solution, stats;
//        if (args.length != 5) {
//            System.out.println("Niepoprawne parametry");
//            return;
//        }
//        algorithm = args[0];
//        additionalInfo = args[1];
//        input = args[2];
//        solution = args[3];
//        stats = args[4];
//
//        Solver solver = new DfsSolver();

//        switch (algorithm.toLowerCase()) {
//            case "bfs" -> System.out.println("BFS");
//            case "dfs" -> solver = new DfsSolver();
//            case "astr" -> System.out.println("ASTR");
//        }

        /***
         * TEST
         */
//        short[][] testTab = {
//                {0,2},
//                {1,3}};
//        short[][] testTab = {{1,2,3},{4,5,6},{0,8,7}};
//        short[][] testTab = {
//                {1, 6, 2, 0},
//                {9, 5, 15, 3},
//                {10, 7, 12, 11},
//                {13, 14, 4, 8}
//        };
//        ResultSet set = solver.solve("DRUL",testTab);
//        System.out.println(set.generateReport());
        /***
         * END TEST
         */
        //Parse file with name as in 'input'
//        String filename = input;
//        File dir = new File("input");
//        System.out.println(dir.getAbsolutePath());
//        String[] fnames = dir.list();
//        int countFiles = fnames.length;
//        int tmpCount = 0;
//        for (int i = 0; i<100; i++) {
//            switch (algorithm.toLowerCase()) {
//                case "bfs" -> System.out.println("BFS");
//                case "dfs" -> solver = new DfsSolver();
//                case "astr" -> System.out.println("ASTR");
//            }
//            short[][] tab = Parser.parse(dir.getPath() + "\\" + fnames[i]);
//
//            //save result to file with name as in solution
//            try {
//                ResultSet s = solver.solve(additionalInfo, tab);
//                tmpCount++;
//                System.out.println(s.generateReport());
//            } catch (RuntimeException ex) {
//                System.out.println("Nie udało się " + fnames[i]);
//            }
//        }
//        System.out.println("Rozwiązano: " + tmpCount + " z: " + 100);

//        try {
//            FileOutputStream outputStream = new FileOutputStream(solution);
//            byte[] strToBytes = "PLIK Z WYNIKAMI".getBytes();
//            outputStream.write(strToBytes);
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        //Save stats to file 'stats'
//        ResultSet s = new ResultSet(
//                "LDUPLLPDLDLD"
//                , 230,
//                150,
//                24,
//                1345672);
//        try {
//            FileOutputStream outputStream = new FileOutputStream(stats);
//            byte[] strToBytes = s.generateReport().getBytes();
//            outputStream.write(strToBytes);
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // ----------------------------------------------------

//        short[][] testTab = {
//                {1, 6, 2, 0},
//                {9, 5, 15, 3},
//                {10, 7, 12, 11},
//                {13, 14, 4, 8}
//        };


        short[][] testTab = {
                {1, 2, 3, 4},
                {5,6,7,8},
                {9,10,11,12},
                {13, 0,14, 15}
        };

        Solver solver = new AcrossSolver();
        solver.solve("DRUL",testTab);












    }
}
