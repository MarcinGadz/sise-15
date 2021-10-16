package app;

import java.util.Map;

public class Node {
    private short[][] tab;
    //If parent is null, node is ROOT
    private Node parent;
    private Map<Character, Node> children;
    private int numberOfMoves;

    public void move(Character direction) {
        numberOfMoves++;
        short x0 = -1, y0 = -1, toX, toY;
        //find location of 0
        for (short i = 0; i < tab.length; i++) {
            for (short j = 0; j < tab[i].length; j++) {
                if(tab[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                }
            }
        }
        switch (direction) {
            case 'L' -> {
                toX = (short) (x0 - 1);
                toY = y0;
            }
            case 'R' -> {
                toX = (short) (x0 + 1);
                toY = y0;
            }
            case 'U' -> {
                toY = (short) (y0 + 1);
                toX = x0;
            }
            case 'D' -> {
                toY = (short) (y0 - 1);
                toX = x0;
            }
            default -> throw new IllegalArgumentException("Bad direction");
        }
        if (toX < 0 || toY < 0 || toX > tab[0].length || toY > tab.length) {
            throw new IndexOutOfBoundsException("Something went wrong");
        }
        short tmp = tab[x0][y0];
        tab[x0][y0] = tab[toX][toY];
        tab[toX][toY] = tmp;
    }


    public Node(short[][] tab, Node parent) {
        this.tab = tab;
        if (parent != null) {
            this.parent = parent;
            this.numberOfMoves = parent.getNumberOfMoves();
        }
    }

    public Node createChild(short[][] content, Character move, Node parent) {
        Node child = new Node(content, parent);
        parent.getChildren().put(move, child);
        return child;
    }



    /*** Getters ***/

    public short[][] getTab() {
        return tab;
    }

    public Node getParent() {
        return parent;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
}
