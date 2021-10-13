package solver;

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

    public String generateReport() {
        StringBuilder b = new StringBuilder();
        b.append(solution.length());
        b.append("\n");
        b.append(visited);
        b.append('\n');
        b.append(checked);
        b.append('\n');
        b.append(maxRecursionDepth);
        b.append('\n');
        String tmp = (computeTimeMicros / 1000)  + "," + (computeTimeMicros % 1000);
        b.append(tmp);
        return b.toString();
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
}
