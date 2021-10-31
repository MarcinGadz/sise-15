package app;


import app.solver.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
//       String algorithm, additionalInfo, input, solution, stats;
//       if (args.length != 5) {
//           System.out.println("Niepoprawne parametry");
//           return;
//       }
//       algorithm = args[0];
//       additionalInfo = args[1];
//       input = args[2];
//       solution = args[3];
//       stats = args[4];
//
//       Solver solver = new DfsSolver();
//
//        switch (algorithm.toLowerCase()) {
//            case "bfs" -> solver = new BfsSolver();
//            case "dfs" -> solver = new DfsSolver();
//            case "astr" -> solver = new AstrSolver();
//        }


//        short[][] tab = Parser.parse(input);
//        ResultSet set = solver.solve(additionalInfo, tab);
//
//
//        try {
//            FileOutputStream outputStream = new FileOutputStream(stats);
//            outputStream.write(set.generateAdditionalReport().getBytes());
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        File dir = new File("input");
        System.out.println(dir.getAbsolutePath());
        String[] fnames = dir.list();
        int countFiles = fnames.length;
        int tmpCount = 0;
//        for (int i = 0; i < 4458; i++) {
//            short[][] tab = Parser.parse(dir.getPath() + "\\" + fnames[i]);
//
//            Solver solver = new DfsSolver();
//            String additionalInfo = "DULR";
//            //save result to file with name as in solution
//            try {
//                ResultSet s = solver.solve(additionalInfo, tab);
//                tmpCount++;
//                System.out.println(s.generateSolutionReport());
//            } catch (RuntimeException ex) {
//                System.out.println("Nie udało się " + fnames[i]);
//            }
//        }
//
//        System.out.println("Rozwiązano DFS: " + tmpCount + " z: " + 4458);
//


        for (int i = 0; i < 4458; i++) {
            short[][] tab = Parser.parse(dir.getPath() + "\\" + fnames[i]);

            Solver solver = new BfsSolver();
            String additionalInfo = "DULR";
            //save result to file with name as in solution
            try {
                ResultSet s = solver.solve(additionalInfo, tab);
                tmpCount++;
                System.out.println(s.generateSolutionReport());
            } catch (RuntimeException ex) {
                System.out.println("Nie udało się " + fnames[i]);
            }
        }

        System.out.println("Rozwiązano BFS: " + tmpCount + " z: " + 4458);

    }
}
