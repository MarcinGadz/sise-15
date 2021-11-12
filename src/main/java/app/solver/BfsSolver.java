package app.solver;

import app.Node;

import java.util.ArrayList;

public class BfsSolver extends Solver {

    private char[] priorities;
    ResultSet results;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        results = new ResultSet();
        priorities = strategy.toUpperCase().toCharArray();
        Long startTime = System.nanoTime();
        Node n = new Node(tab, null);
        explore(n);
        Long finishTime = System.nanoTime();
        results.setComputeTimeMicros((finishTime - startTime) / 1000);
        if (results.getSolution() == null) {
            throw new RuntimeException("Nie udało się");
        }
        return results;
    }

    private void explore(Node n) {
        if (isSolved(n.getTab())) {
            return;
        }

        results.visitedIncrease();
        results.checkedIncrease();
        ArrayList<Node> first = new ArrayList<>();
        ArrayList<Node> second = new ArrayList<>();
        first.add(n);

        while (true) {
            for (Node node : first) {
                for (char c : priorities) {
                    if (node.canCreateChildInDirection(c)) {
                        Node child = node.createChild();
                        child.move(c);
                        results.setMaxRecursionDepth(Math.max(results.getMaxRecursionDepth(), child.getDepth() ));
                        results.visitedIncrease();
                        results.checkedIncrease();
                        if (isSolved(child.getTab())) {
                            results.setSolution(child.getPath());
                            return;
                        }
                        second.add(child);
                    }
                }

            }
            first = new ArrayList<>(second);
            second = new ArrayList<>();
        }
    }
}
