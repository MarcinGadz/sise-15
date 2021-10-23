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

        Node root = new Node(tab, null);
        while (!isSolved(root.getTab())) {
            visited.add(SerializationUtils.clone(root));
            //param doesn't make any change in working of alghoritm
            // (it could be random permutation of these letters)
            root.generateChildren(new char[]{'L', 'R', 'U', 'D'});
            LinkedList<Node> children = new LinkedList<>(root.getChildren());
            //find node with minimum distance from goal
            root = children.stream().min(c::calc).orElseThrow(RuntimeException::new);
            while(visited.contains(root)) {
                children.remove(root);
                root = children.stream().min(c::calc).orElseThrow(RuntimeException::new);
            }
            System.out.println("\n\n\n");
            root.printPrettyTab();
        }
        System.out.println(root.getPath());
        return null;
    }

    private static int hammingDist(Node curr, Node goal) {
        /***
         * Distance is number of places in which tabs have different content
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
