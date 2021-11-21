package app.solver;

import app.Node;


public class DfsSolver extends Solver {
    private final int maxDepth = 25;
    private char[] priorities;
    private ResultSet results;
    private Long startTime;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {
        results = new ResultSet();
        priorities = strategy.toUpperCase().toCharArray();
        startTime = System.nanoTime();
        Node n = new Node(tab, null);
        explore(n);
        Long finishTime = System.nanoTime();
        results.setComputeTimeMicros((finishTime - startTime) / 1000);
        if (results.getSolution() == null) {
            throw new RuntimeException("Nie udało się");
        }
        return results;
    }

    private boolean finished;



    private void explore(Node n) {
        // Jeśli osiągnięto maksymalną określoną głębokość rekurencji - przerwij
        if (finished || n.getDepth() > maxDepth) {
            return;
        }
        //Ustaw maksymalną osiągniętą głębokość rekurencji
        //o ile aktualna jest wyższa niż aktualnie zapisana
        results.setMaxRecursionDepth(Math.max(results.getMaxRecursionDepth(), n.getDepth()));
        n.generateChildren(priorities);
        //Zwiększ liczbę odwiedzonych stanów
        results.visitedIncrease();
        n.getChildren().forEach(child -> {
            if (isSolved(child.getTab())) {
                finished = true;
                results.setSolution(child.getPath());
                return;
            }
            explore(child);
        });
        results.checkedIncrease();
    }
}
