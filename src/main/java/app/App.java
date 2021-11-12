package app;


import app.solver.*;


import java.io.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
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
//
//
//        short[][] tab = Parser.parse(input);
//        ResultSet set = solver.solve(additionalInfo, tab);
//        System.out.println(set.generateAdditionalReport());
//
//        PrintWriter zapis = new PrintWriter(solution);
//        zapis.println(set.generateSolutionReport());
//        zapis.close();
//        zapis = new PrintWriter(stats);
//        zapis.println(set.generateAdditionalReport());
//        zapis.close();
//

//
//        try {
//            FileOutputStream outputStream = new FileOutputStream(stats);
//            outputStream.write(set.generateAdditionalReport().getBytes());
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



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
//        System.out.println("Rozwiązano BFS: " + tmpCount + " z: " + 4458);
//
//    }
//
        sss("input1");
        System.out.println("done");
        sss("input2");
        System.out.println("done");
        sss("input3");
        System.out.println("done");
        sss("input4");
        System.out.println("done");
        sss("input5");
        System.out.println("done");
        sss("input6");
        System.out.println("done");
        sss("input7");
        System.out.println("done");


}

static void sss(String p) {

    System.out.println(p);
    File dir = new File(p);
    System.out.println(dir.getAbsolutePath());
    String[] fnames = dir.list();
    int countFiles = fnames.length;
    int tmpCount = 0;

    int ulrd = 0,uldr=0,rdul=0,rdlu=0,lurd=0,ludr=0,drul=0,drlu=0;
    int oulrd = 0,ouldr=0,ordul=0,ordlu=0,olurd=0,oludr=0,odrul=0,odrlu=0;
    int pulrd = 0,puldr=0,prdul=0,prdlu=0,plurd=0,pludr=0,pdrul=0,pdrlu=0;
    int mulrd = 0,muldr=0,mrdul=0,mrdlu=0,mlurd=0,mludr=0,mdrul=0,mdrlu=0;
    int tulrd = 0,tuldr=0,trdul=0,trdlu=0,tlurd=0,tludr=0,tdrul=0,tdrlu=0;

    for (int i = 0; i < countFiles; i++) {
        short[][] tab = Parser.parse(dir.getPath() + "\\" + fnames[i]);
        Solver solver = new DfsSolver();
        //save result to file with name as in solution
        try {
            ResultSet s = solver.solve("ULRD", tab);
            ulrd += s.getSolution().length();
            oulrd += s.getVisited();
            pulrd += s.getChecked();
            mulrd += s.getMaxRecursionDepth();
            tulrd += s.getComputeTimeMicros();


            s = solver.solve("ULDR", tab);
            uldr += s.getSolution().length();
            ouldr += s.getVisited();
            puldr += s.getChecked();
            muldr += s.getMaxRecursionDepth();
            tuldr += s.getComputeTimeMicros();

            s = solver.solve("RDUL", tab);
            rdul += s.getSolution().length();
            ordul += s.getVisited();
            prdul += s.getChecked();
            mrdul += s.getMaxRecursionDepth();
            trdul += s.getComputeTimeMicros();


            s = solver.solve("RDLU", tab);
            rdlu += s.getSolution().length();
            ordlu += s.getVisited();
            prdlu += s.getChecked();
            mrdlu += s.getMaxRecursionDepth();
            trdlu += s.getComputeTimeMicros();

            s = solver.solve("LURD", tab);
            lurd += s.getSolution().length();
            olurd += s.getVisited();
            plurd += s.getChecked();
            mlurd += s.getMaxRecursionDepth();
            tlurd += s.getComputeTimeMicros();

            s = solver.solve("LUDR", tab);
            ludr += s.getSolution().length();
            oludr += s.getVisited();
            pludr += s.getChecked();
            mludr += s.getMaxRecursionDepth();
            tludr+= s.getComputeTimeMicros();

            s = solver.solve("DRUL", tab);
            drul += s.getSolution().length();
            odrul += s.getVisited();
            pdrul += s.getChecked();
            mdrul += s.getMaxRecursionDepth();
            tdrul += s.getComputeTimeMicros();

            s = solver.solve("DRLU", tab);
            drlu += s.getSolution().length();
            odrlu += s.getVisited();
            pdrlu += s.getChecked();
            mdrlu += s.getMaxRecursionDepth();
            tdrlu += s.getComputeTimeMicros();


            tmpCount++;

        } catch (RuntimeException ex) {
            System.out.println("Nie udało się " + fnames[i]);
        }
    }


    System.out.println(
            "\n" + p + "\n" +

            "\n Srednia ilość dlugosc 8 kolejnosci " +

            "\n ULRD: " + ulrd/countFiles +
            "\n ULDR: " + uldr/countFiles +
            "\n RDUL: " + rdul/countFiles +
            "\n RDLU: " + rdlu/countFiles +
            "\n LURD: " + lurd/countFiles +
            "\n LUDR: " + ludr/countFiles +
            "\n DRUL: " + drul/countFiles +
            "\n DRLU: " + drlu/countFiles +
                    "\n Srednia ilość odwiedzonych 8 kolejnosci " +

            "\n ULRD: " + oulrd/countFiles +
            "\n ULDR: " + ouldr/countFiles +
            "\n RDUL: " + ordul/countFiles +
            "\n RDLU: " + ordlu/countFiles +
            "\n LURD: " + olurd/countFiles +
            "\n LUDR: " + oludr/countFiles +
            "\n DRUL: " + odrul/countFiles +
            "\n DRLU: " + odrlu/countFiles +

    "\n Srednia ilość przetworzonych 8 kolejnosci " +

            "\n ULRD: " + pulrd/countFiles +
            "\n ULDR: " + puldr/countFiles +
            "\n RDUL: " + prdul/countFiles +
            "\n RDLU: " + prdlu/countFiles +
            "\n LURD: " + plurd/countFiles +
            "\n LUDR: " + pludr/countFiles +
            "\n DRUL: " + pdrul/countFiles +
            "\n DRLU: " + pdrlu/countFiles +

            "\n Srednia ilość max rekursja 8 kolejnosci " +

                    "\n ULRD: " + mulrd/countFiles +
                    "\n ULDR: " + muldr/countFiles +
                    "\n RDUL: " + mrdul/countFiles +
                    "\n RDLU: " + mrdlu/countFiles +
                    "\n LURD: " + mlurd/countFiles +
                    "\n LUDR: " + mludr/countFiles +
                    "\n DRUL: " + mdrul/countFiles +
                    "\n DRLU: " + mdrlu/countFiles +

            "\n Srednia ilość czas 8 kolejnosci " +

            "\n ULRD: " + tulrd/countFiles +
            "\n ULDR: " + tuldr/countFiles +
            "\n RDUL: " + trdul/countFiles +
            "\n RDLU: " + trdlu/countFiles +
            "\n LURD: " + tlurd/countFiles +
            "\n LUDR: " + tludr/countFiles +
            "\n DRUL: " + tdrul/countFiles +
            "\n DRLU: " + tdrlu/countFiles);


}






}
