package app.solver;

import app.Node;

public class AcrossSolver extends Solver {

    private char[] priorities;
    short level = 0;
    Node parent;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        ResultSet resultSet = new ResultSet();
        priorities = new StringBuilder(strategy.toUpperCase()).reverse().toString().toCharArray();
        Node n = new Node(tab, null);
        parent = n;
        explore(n);
        return resultSet;
    }

    private boolean checklevel(Node node) {
        if(!node.isVis() && node.getDepth()==level-1){
            return false;
        }
        else {
            if(node.getChildren()==null) return false;
            for( Node n : node.getChildren()) {
                return checklevel(n);
            }
        }
        return true;
    }

    private void clearAllTree(Node node) {
        if(node.getChildren().size()==0){
            return;
        }
        else {
            for( Node n : node.getChildren()) {
                n.vis = false;
                clearAllTree(n);
            }
        }
    }


    private boolean finished;

    private void explore(Node n) {
        if(finished) {
            return;
        }
        n.generateChildren(priorities);
        level++;
        for (Node nodes : n.getChildren()){
            if(isSolved(nodes.getTab())) {
                finished=true;
                for (int i = 0; i < nodes.getTab().length; i++) {
                    for (int j = 0; j < nodes.getTab()[0].length; j++) {
                        System.out.print(nodes.getTab()[i][j]);
                    }
                }
                return; // jest git
            }
            nodes.vis = true;
            System.out.println(nodes.getTab());
        }
        if(checklevel(parent)) return;
        for ( Node nodes : n.getChildren()) {
            if(finished) return;
            explore(nodes);
        }
    }
}
