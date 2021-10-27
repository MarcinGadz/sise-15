package app.solver;

import app.Node;

import java.util.LinkedList;
import java.util.List;

public class DfsSolver extends Solver {
    private final int maxDepth = 25;
    private char[] priorities;
    private ResultSet results;
    private Long startTime;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {
        visited = new LinkedList<>();
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

    List<Node> visited = new LinkedList<>();

    private void explore(Node n) {
        // Jeżeli dany układ już pojawił się w sprawdzanej gałęzi - nie przetwarzaj go ponownie
        if (n.wasThatTabInBranch()) {
            return;
        }
        // Jeśli dany układ nie zostanie rozwiązany w 10s, przerwij
        if ((System.nanoTime() - startTime) / 1000000000 >= 100000) {
            throw new RuntimeException("Upłynął maksymalny czas");
        }
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
        results.setChecked(results.getChecked() + 1);
    }
}
