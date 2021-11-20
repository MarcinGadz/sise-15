package app.solver;

import app.Node;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

public class AstrSolver extends Solver {
    private ResultSet s;
    private Calculator c;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {
        generateTarget(tab[0].length, tab.length);
        targetNode = new Node(target, null);
        visited = new LinkedList<>();
        s = new ResultSet();
        if (strategy.toLowerCase(Locale.ROOT).equals("hamm")) {
            c = AstrSolver::hammingDist;
        } else {
            c = AstrSolver::manhDist;
        }
        Long startTime = System.nanoTime();
        Node node = new Node(tab, null);
        s.checkedIncrease();
        toProcess.add(node);
        node = astr(node);
        s.setSolution(node.getPath());
        Long finishTime = System.nanoTime();
        s.setComputeTimeMicros((finishTime - startTime) / 1000);
        return s;
    }

    private Node targetNode;
    List<Node> visited;

    List<Node> toProcess = new ArrayList<>();
    List<Node> closed = new ArrayList<>();

    private Node astr(Node node) {
        if (!isSolved(node.getTab())) {
            Node n = node;

            for (Node p : toProcess) {

                if (p.score == -1) {
                    p.score = c.calc(p, targetNode);
                }

                s.visitedIncrease();

                if (p.getDepth() > s.getMaxRecursionDepth()) s.setMaxRecursionDepth(p.getDepth());
                if (isSolved(p.getTab())) {
                    return p;
                }
            }
            //find node with minimum distance from goal
            n = toProcess.stream().min(Comparator.comparingInt(a -> a.score)).get();
            s.checkedIncrease();
            closed.add(n);
            toProcess.remove(n);
            //param doesn't make any change in working of alghoritm
            // (it could be random permutation of these letters)
            n.generateChildren(new char[]{'L', 'R', 'U', 'D'});

            for (Node child : n.getChildren()) {
                if (isSolved(child.getTab())) {
                    s.setSolution(node.getPath());
                    return child;
                }
                if (closed.contains(child)) continue;
                toProcess.add(child);
            }
            return astr(n);
        }
        return node;
    }

    private static int hammingDist(Node curr, Node goal) {
        /*
         Distance is number of places in which tabs have different content
         */
        int dist = 0;
        for (int i = 0; i < curr.getTab().length; i++) {
            for (int j = 0; j < curr.getTab()[i].length; j++) {
                if (curr.getTab()[i][j] != goal.getTab()[i][j]) {
                    dist++;
                }
            }
        }
        return dist;
    }

    @FunctionalInterface
    private interface Calculator {
        int calc(Node curr, Node goal);
    }

    private static int manhDist(Node curr, Node goal) {
        int dist = 0;
        int goalX, goalY;
        for (int i = 0; i < curr.getTab().length; i++) {
            for (int j = 0; j < curr.getTab()[i].length; j++) {
                if (curr.getTab()[i][j] != i * curr.getTab()[i].length + j + 1 && curr.getTab()[i][j] != 0) {
                    goalX = (curr.getTab()[i][j] - 1) / curr.getTab()[i].length;
                    if (goalX < 0) goalX = 0;
                    goalY = (curr.getTab()[i][j] - 1) % (curr.getTab().length);
                    dist += Math.abs(goalX - i) + Math.abs(goalY - j);
                } else if (curr.getTab()[i][j] == 0) {
                    goalX = curr.getTab()[i].length - 1;
                    goalY = curr.getTab().length - 1;
                    dist += Math.abs(goalX - i) + Math.abs(goalY - j);
                }
            }

        }
        return dist;
    }

}
