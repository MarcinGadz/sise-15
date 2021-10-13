import org.apache.commons.lang3.ArrayUtils;
import solver.ResultSet;

public class App {
    public static void main(String[] args) {
        //example
        String filename = "test.txt";
        short[][] tab = Parser.parse(filename);
        System.out.println(ArrayUtils.toString(tab));
        ResultSet s = new ResultSet(
                "LDUPLLPDLDLD"
                ,230,
                150,
                24,
                1345672);
        System.out.println(s.generateReport());
    }
}
