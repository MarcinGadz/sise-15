package app.solver;

public class ResultSet {
    /***
     * Class to represent required data about resolved puzzle
     */
    private String solution;
    private int visited;
    private int checked;
    private int maxRecursionDepth;
    private long computeTimeMicros;

    public ResultSet(String solution, int visited,
                     int checked, int maxRecursionDepth, long computeTimeMilis) {
        this.solution = solution;
        this.visited = visited;
        this.checked = checked;
        this.maxRecursionDepth = maxRecursionDepth;
        this.computeTimeMicros = computeTimeMilis;
    }

    public ResultSet() {
    }

    public String generateAdditionalReport() {
        String tmp = (computeTimeMicros / 1000) + "," + (computeTimeMicros % 1000);
        return solution.length() +
                "\n" +
                visited +
                '\n' +
                checked +
                '\n' +
                maxRecursionDepth +
                '\n' +
                tmp;
    }

    public String generateSolutionReport() {
        if (solution==null) return "-1";
        return solution.length() +
                "\n" +
                solution;
    }

    public void visitedIncrease() {
        visited++;
    }

    public void checkedIncrease() {
        checked++;
    }

    public String getSolution() {
        return solution;
    }

    public int getVisited() {
        return visited;
    }

    public int getChecked() {
        return checked;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getComputeTimeMicros() {
        return computeTimeMicros;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public void setMaxRecursionDepth(int maxRecursionDepth) {
        this.maxRecursionDepth = maxRecursionDepth;
    }

    public void setComputeTimeMicros(long computeTimeMicros) {
        this.computeTimeMicros = computeTimeMicros;
    }
}
