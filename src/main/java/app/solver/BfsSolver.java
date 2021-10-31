package app.solver;

import app.Node;
import java.util.ArrayList;

public class BfsSolver extends Solver {

    private char[] priorities;
    ResultSet results;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        results = new ResultSet();
        priorities = new StringBuilder(strategy.toUpperCase()).reverse().toString().toCharArray();
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
        if(isSolved(n.getTab())){
            return;
        }

        ArrayList<Node> first = new ArrayList<>();
        ArrayList <Node> second = new ArrayList<>();
        first.add(n);

        while(true) {
            for (int i = 0; i < first.size() ; i++) {
                for (char c:priorities) {
                    if(first.get(i).canCreateChildInDirection(c)) {
                        Node child =first.get(i).createChild();
                        child.move(c);
                        if(isSolved(child.getTab())){
                            System.out.println("Solution: ");
                            child.printPrettyTab();
                            results.setSolution(child.getPath());
                            System.out.println(child.getPath());
                            return;
                        }
                        second.add(child);
                    }
                }
            }
            first = second;
        }
    }
}
