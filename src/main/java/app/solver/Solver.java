package app.solver;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Solver {
    /***
     *
     * @param strategy
     * @param tab
     * @return class with all data about computed solution
     */
    private short[][] target;

    public abstract ResultSet solve(String strategy, short[][] tab);

    public boolean isSolved(short[][] tab) {
        if (target == null) {
            generateTarget(tab[0].length, tab.length);
        }
        return Arrays.deepEquals(tab, target);
    }

    private void generateTarget(int xlen, int ylen) {
        target = new short[ylen][xlen];
        for (short i = 0; i < xlen; i++) {
            for (short j = 0; j < ylen; j++) {
                if (i*j == (xlen-1)*(ylen-1)) {
                    target[j][i] = 0;
                    break;
                }
                target[j][i] = (short) (i + 1 + j * xlen);
            }
        }
    }

}
