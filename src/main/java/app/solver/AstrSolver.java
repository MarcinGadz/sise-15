package app.solver;

import app.Node;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

public class AstrSolver extends Solver {
    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        Calculator c;
        if (strategy.toLowerCase(Locale.ROOT).equals("hamm")) {
            c = AstrSolver::hammingDist;
        } else {
            c = AstrSolver::manhDist;
        }

        List<Node> visited = new LinkedList<>();

        Node node = new Node(tab, null);
        while (!isSolved(node.getTab())) {
            visited.add(SerializationUtils.clone(node));
            //param doesn't make any change in working of alghoritm
            // (it could be random permutation of these letters)
            node.generateChildren(new char[]{'L', 'R', 'U', 'D'});
            LinkedList<Node> children = new LinkedList<>(node.getChildren());
            //find node with minimum distance from goal
            node = children.stream().min(c::calc).orElseThrow(RuntimeException::new);
            while (visited.contains(node)) {
                children.remove(node);
                node = children.stream().min(c::calc).orElseThrow(RuntimeException::new);
            }
        }
        return null;
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
        return Math.abs(curr.getX0() - goal.getX0()) + Math.abs(curr.getY0() - goal.getY0());
    }

}
