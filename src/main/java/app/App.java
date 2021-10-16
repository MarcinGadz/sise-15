package app;

import app.solver.ResultSet;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        for (String s : args) {
            System.out.println(s);
        }
        //example
        String filename = "test.txt";
        short[][] tab = Parser.parse(filename);
        System.out.println(Arrays.toString(tab));
        ResultSet s = new ResultSet(
                "LDUPLLPDLDLD"
                , 230,
                150,
                24,
                1345672);
        System.out.println(s.generateReport());
    }
}
