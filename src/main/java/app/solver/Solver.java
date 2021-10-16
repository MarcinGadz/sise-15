package app.solver;
public interface Solver {
    /***
     *
     * @param strategy
     * @param tab
     * @return class with all data about computed solution
     */
    public app.solver.ResultSet solve(String strategy, short[][] tab);
}
