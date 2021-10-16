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
            case 'L':
                toX = (short) (x0 - 1);
                break;
            case 'R':
                toX = (short) (x0 + 1);
                break;
            case 'U':
                toY = (short) (y0 + 1);
                break;
            case 'D':
                toY = (short) (y0 - 1);
        }
        if (toX < 0 || toY < 0 || toX > tab[toY].length || toY > tab.length)
        swapFields(x0, y0, toX, toY);
    }

    private void swapFields(byte fromX, byte fromY, byte toX, byte toY) {
        short tmp = tab[fromX][fromY];
        tab[fromX][fromY] = tab[toX][toY];
        tab[toX][toY] = tmp;
    }

    public Node(short[][] tab, Node parent) {
        this.tab = tab;
        if (parent != null) {
            this.parent = parent;
        }
    }

    public Node createChild(short[][] content, Character move, Node parent) {
        Node child = new Node(content, parent);
        return null;

    }
}
