package app.solver;

import app.Node;

public class AcrossSolver extends Solver {

    private char[] priorities;
    short level = 0;
    Node parent;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        ResultSet resultSet = new ResultSet();
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
        if(!checklevel(parent)) return;

        n.generateChildren(priorities);
        level++;
        for (Node nodes : n.getChildren()){
            if(isSolved(nodes.getTab())) {
                finished=true;
                return; // jest git
            }
            nodes.vis = true;
            System.out.println(nodes.getTab());
        }
        for ( Node nodes : n.getChildren()) {
            if(finished) return;
            explore(nodes);
        }
//
//
//
//
//
//
//
//
//
//        while(!isSolved(n.getTab())) {
//
//            if(checklevel()
//
//            if(n.getChildren().size()==0) {
//                return;
//            }
//
//
//            n.vis = true;
//
//
//
//
//            if(n.getDepth()!=level){
//                for(Node nodes : n.getChildren()){
//                    explore(nodes);
//                }
//            }
//            else {
//                n.generateChildren(priorities);
//                n.vis= false;
//                level++;
//            }
//
//            for (Node nodes : n.getChildren()){
//                if(isSolved(nodes.getTab())) {
//                    nodes.vis=true;
//                    return; // jest git
//                }
//            }
//
//            for (Node nodes : n.getChildren()){
//                explore(nodes);
//            }
//
//        }


    }





}
