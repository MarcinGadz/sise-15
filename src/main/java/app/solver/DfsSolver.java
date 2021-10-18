package app.solver;

import app.Node;

public class DfsSolver extends Solver {
    private final int maxDepth = 25;
    private char[] priorities;
    private ResultSet results;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {
        results = new ResultSet();

        priorities = strategy.toUpperCase().toCharArray();
        Long startTime = System.nanoTime();
        Node n = new Node(tab, null);
        n = explore(n);
        Long finishTime = System.nanoTime();
        String path = n.getPath();
        results.setSolution(path);
        results.setComputeTimeMicros((finishTime - startTime) / 1000);
        results.setMaxRecursionDepth(9999);
        results.setChecked(1234);
        results.setVisited(3333);
        return results;
    }

    private Node explore(Node n) {
        while (!isSolved(n.getTab())) {
            if (n.getDepth() < maxDepth) {
                for (char priority : priorities) {
                    if (n.canCreateChildInDirection(priority)) {
                        n = n.createChild(priority);
                        n.move(priority);
                        if(isSolved(n.getTab())) {
                            return n;
                        }
                        return explore(n);
                    }
                }
            } else {
                return n;
            }
        }
        System.out.println("SOLVED");
        return n;
    }
}
