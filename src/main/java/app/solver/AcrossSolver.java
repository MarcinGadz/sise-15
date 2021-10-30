package app.solver;

import app.Node;
import java.util.ArrayList;

public class AcrossSolver extends Solver {

    private char[] priorities;


    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        ResultSet resultSet = new ResultSet();
        priorities = new StringBuilder(strategy.toUpperCase()).reverse().toString().toCharArray();
        Node n = new Node(tab, null);
        explore(n);
        return resultSet;
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
