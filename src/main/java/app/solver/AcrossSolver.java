package app.solver;

import app.Node;

public class AcrossSolver extends Solver {

    private char[] priorities;

    @Override
    public ResultSet solve(String strategy, short[][] tab) {

        ResultSet resultSet = new ResultSet();
        Node n = new Node(tab, null);
        n = explore(n);
        return resultSet;
    }



    private Node explore(Node n) {

        while(!isSolved(n.getTab())){
            if(n.getChildren().size()<4){
                 Node child = n.createChild(priorities[n.getChildren().size()]);
                 if (isSolved(child.getTab())) {
                     return child;
                 }
                 else {
                     explore(n);
                 }
            }
            else {
                n.tagAsChecked();
                n = n.getChildren().get(priorities[n.checked-1]);
                explore(n);
            }

        }
        System.out.println("SOLVED");
        return n;
    }


}
