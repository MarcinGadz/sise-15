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

    private boolean found;
    private boolean checklevel(Node node) {
        if(found) return false;
        if(!node.isVis() && node.getDepth()==level-1){
            found=true;
            return false;
        }
        if(node.getChildren()==null) return true;
        for( Node n : node.getChildren()) {
            if (!checklevel(n)) {
                found = true;
                break;
            }
        }
        return false;
    }

    private void clearAllTree(Node node) {
        node.vis=false;
        if(node.getChildren()==null) return;
        for( Node n : node.getChildren()) {
            n.vis=false;
            clearAllTree(n);
        }

    }


//    private boolean finished;

//    private void explore(Node n) {
//        if(finished) {
//            return;
//        }
//        if(n.isVis()) return;
//        n.generateChildren(priorities);
//        level++;
//        for (Node nodes : n.getChildren()){
//            if(isSolved(nodes.getTab())) {
//                finished=true;
//                nodes.print();
//                return; // jest git
//            }
//            nodes.vis = true;
//            nodes.print();
//        }
//        if(checklevel(parent)) return;
//        for ( Node nodes : n.getChildren()) {
//            if(finished) return;
//            clearAllTree(nodes);
//            explore(nodes);
//        }
//    }
    boolean finished = false;
    private void explore(Node node) {

        if(finished) return;
        if(checklevel(node)) return;
        node.generateChildren(priorities);
        for (Node nodes : node.getChildren()){
            if(isSolved(nodes.getTab())) finished=true;
        }
        level++;
        node.vis = true;
        for (Node nodes : node.getChildren()){
            explore(nodes);
        }


    }




}
