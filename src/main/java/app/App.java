package app;


import app.solver.*;


import java.io.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
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

       Solver solver = new DfsSolver();

        switch (algorithm.toLowerCase()) {
            case "bfs" -> solver = new BfsSolver();
            case "dfs" -> solver = new DfsSolver();
            case "astr" -> solver = new AstrSolver();
        }


        short[][] tab = Parser.parse(input);
        ResultSet set = solver.solve(additionalInfo, tab);
        PrintWriter zapis = new PrintWriter(solution);
        if (set == null) {
            zapis.println("-1");
        }
        else {
            zapis.println(set.generateSolutionReport());
            zapis.close();
            zapis = new PrintWriter(stats);
            zapis.println(set.generateAdditionalReport());
        }
        zapis.close();

    }





}
