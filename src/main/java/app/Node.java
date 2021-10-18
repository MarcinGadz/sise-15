package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private short[][] tab;
    //If parent is null, node is ROOT
    private Node parent;
    private Map<Character, Node> children;
    private int numberOfMoves;
    private int depth;
    private String path = "";
    //Location of empty cell
    private short x0, y0;

    public boolean canCreateChildInDirection(Character direction) {
        for (short i = 0; i < tab.length; i++) {
            for (short j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == 0) {
                    x0 = j;
                    y0 = i;
                }
            }
        }
        short toX, toY;
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
                toY = (short) (y0 - 1);
                toX = x0;
            }
            case 'D' -> {
                toY = (short) (y0 + 1);
                toX = x0;
            }
            default -> {
                return false;
            }
        }
        if(tab[y0][x0] != 0 ) {
            throw new RuntimeException();
        }
        return toX >= 0 && toY >= 0 && toX < tab[0].length && toY < tab.length;
    }

    public void move(Character direction) {
        numberOfMoves++;
        short toX, toY;
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
                toY = (short) (y0 - 1);
                toX = x0;
            }
            case 'D' -> {
                toY = (short) (y0 + 1);
                toX = x0;
            }
            default -> throw new IllegalArgumentException("Bad direction");
        }
        if (toX < 0 || toY < 0 || toX >= tab[0].length || toY >= tab.length) {
            throw new IndexOutOfBoundsException("Something went wrong");
        }
        short tmp = tab[y0][x0];
        tab[y0][x0] = tab[toY][toX];
        tab[toY][toX] = tmp;
        if(tmp != 0) throw new RuntimeException();
        this.path += direction;
        x0 = toX;
        y0 = toY;
    }

    public void printPrettyTab() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Node(short[][] tab, Node parent) {
        this.tab = tab;
        //find location of 0
        for (short i = 0; i < tab.length; i++) {
            for (short j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == 0) {
                    x0 = j;
                    y0 = i;
                }
            }
        }
        if (parent != null) {
            this.parent = parent;
            this.numberOfMoves = parent.getNumberOfMoves();
        }
    }

    public Node createChild(Character move) {
        short[][] tmp = Arrays.copyOf(this.tab, this.tab.length);
        Node child = new Node(tmp, this);
        child.depth = this.getDepth() + 1;
        child.path = this.path;
        if (this.getChildren() == null) {
            this.children = new HashMap<>();
        }
        this.getChildren().put(move, child);
        return child;
    }


    /*** Getters ***/

    public short[][] getTab() {
        return tab;
    }

    public String getPath() {
        return path;
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

    public int getDepth() {
        return depth;
    }
}
