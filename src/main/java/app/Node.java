package app;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.*;

public class Node implements Serializable {
    private final short[][] tab;
    //If parent is null, node is ROOT
    private Node parent;
    private List<Node> children;
    private int numberOfMoves;
    private char move = 'x';
    public short checked;

    //Location of empty cell
    private short x0, y0;


    public void tagAsChecked() {
        checked++;
    }

    public boolean wasThatTabInBranch() {
        Node tmp = this.getParent();
        while (tmp != null) {
            if (Arrays.deepEquals(tmp.tab, this.tab)) {
                return true;
            }
            tmp = tmp.getParent();
        }
        return false;
    }

    public boolean canCreateChildInDirection(Character direction) {
        switch (direction) {
            case 'L' -> {
                return x0 - 1 >= 0;
            }
            case 'R' -> {
                return x0 + 1 < tab[0].length;
            }
            case 'U' -> {
                return y0 - 1 >= 0;
            }
            case 'D' -> {
                return y0 + 1 < tab.length;
            }
            default -> {
                return false;
            }
        }
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
        this.move = direction;
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

    public Node createChild() {
        short[][] tmp = SerializationUtils.clone(this.tab);
        Node child = new Node(tmp, this);
        if (this.getChildren() == null) {
            this.children = new LinkedList<>();
        }
        this.getChildren().add(child);
        return child;
    }

    public void generateChildren(char[] priorities) {
        for (char c : priorities) {
            if (canCreateChildInDirection(c)) {
                Node child = this.createChild();
                child.move(c);
            }
        }
    }

    /*** Getters, hashCode, equals, etc. ***/

    public short[][] getTab() {
        return tab;
    }

    public String getPath() {
        StringBuilder b = new StringBuilder();
        Node tmp = this;
        while (tmp != null) {
            if (tmp.getMove() != 'x') {
                b.append(tmp.getMove());
            }
            tmp = tmp.parent;
        }
        return b.reverse().toString();
    }

    public int getDepth() {
        int depth = 1;
        Node tmp = this;
        while (tmp != null) {
            tmp = tmp.getParent();
            depth++;
        }
        return depth;
    }

    public Character getMove() {
        return this.move;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public short getX0() {
        return x0;
    }

    public short getY0() {
        return y0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Arrays.deepEquals(tab, node.tab) && parent == node.parent;
    }

    @Override
    public int hashCode() {
        int result;
        result = 31 * Arrays.deepHashCode(tab);
        return result;
    }

}
