package app.solver;

import app.Node;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

public class DfsSolver extends Solver {
    private final int maxDepth = 20;
    private char[] priorities;
    private ResultSet results;
    private Long startTime;
    @Override
    public ResultSet solve(String strategy, short[][] tab) {
        results = new ResultSet();
        //reversed bc of stack
        priorities = new StringBuilder(strategy.toUpperCase()).reverse().toString().toCharArray();
        startTime = System.nanoTime();
        Node n = new Node(tab, null);
        explore(n);
        Long finishTime = System.nanoTime();
        results.setComputeTimeMicros((finishTime - startTime) / 1000);
        //TODO
        results.setChecked(1234);
        results.setVisited(3333);
        if(results.getSolution() == null) {
            throw new RuntimeException("Nie udało się");
        }
        return results;
    }
    private boolean finished;

    private void explore(Node n) {
        //Jeśli dany układ nie zostanie rozwiązany w 10s, przerwij
        if((System.nanoTime() - startTime)/1000000000 >= 10000) {
            throw new RuntimeException("Upłynął maksymalny czas");
        }
        if(finished) {
            return;
        }
        if(n.getDepth() > maxDepth) {
            return;
        }
        results.setMaxRecursionDepth(Math.max(results.getMaxRecursionDepth(), n.getDepth()));
        boolean wasChildrenGenerated = false;
        for (Character c : priorities) {
            if(finished) {
                break;
            }
            if(n.canCreateChildInDirection(c)) {
                if(!n.notVisited(c)) {
                    continue;
                }
                if(!wasChildrenGenerated) {
                    n.generateChildren(priorities);
                    wasChildrenGenerated = true;
                }
                Node child = n.getChildren().pop();
                n.addVisited(c);
                if(isSolved(child.getTab())) {
                    finished = true;
                    System.out.println("SOLVED\n\n");
                    results.setSolution(child.getPath());
                    return;
                }
                explore(child);
            }
            results.setChecked(results.getChecked() + 1);
        }

    }




















//    private void explore(Node n) {
//        if(!isSolved(n.getTab()) && !finished) {
//            if (n.getDepth() < maxDepth) {
//                System.out.println(n.getDepth());
//                if(visited.contains(n)) {
//                    n = visited.get(visited.indexOf(n));
//                    return;
//                }
//                else {
//                    visited.add(SerializationUtils.clone(n));
//                }
//                for (char priority : priorities) {
//                    if (n.canCreateChildInDirection(priority) && n.notVisited(priority)) {
//                        System.out.println(n.getVisited());
//                        n.addVisited(priority);
//                        n = n.createChild(priority);
//                        n.move(priority);
//                        n.printPrettyTab();
//                        if(isSolved(n.getTab())) {
//                            System.out.println("SOLVED");
//                            finished = true;
//                            results.setSolution(n.getPath());
//                            return;
//                        }
//                        explore(n);
//                    }
//                }
//            } else {
//                System.out.println("max depth reached");
//            }
//        }
//    }
}
