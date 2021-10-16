public class Puzzle {
    /***
     * Fields
     */

    private short[][] tab;
    private int numberOfMoves;

    public void move(byte fromX, byte fromY, byte toX, byte toY) {
        numberOfMoves++;
        swapFields(fromX, fromY, toX, toY);
    }

    public short[][] getTab() {
        return tab;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    /***
     * Private methods
     */

    private void swapFields(byte fromX, byte fromY, byte toX, byte toY) {
        short tmp = tab[fromX][fromY];
        tab[fromX][fromY] = tab[toX][toY];
        tab[toX][toY] = tmp;
    }


}
