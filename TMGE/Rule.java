package TMGE;

import java.util.HashSet;
import java.util.Set;


/**
 * Abstract Rule class that holds all information about matching and gameover.
 */
public abstract class Rule {
    private static final int SCORE_VALUE = 100;
    // Method to find matches on the board
    // Match class is for Matched tile

    /**
     * Function to run all given match rules and calculate score.
     * @param board
     * @return int (representing accumulated score)
     */
    abstract public int runAllMatch(Board board);

    /**
     * Function to run all gameover checks and determine if the board is in a gameover state.
     * @param board
     * @param turn_number
     * @param score
     * @return boolean isGameOver
     */
    abstract public boolean checkGameOver(Board board, int turn_number, int score);

    /**
     * Provided Sweeping Match Function. Will iterate through board vertically looking for matches of size x.
     * @param board
     * @param matchX this function removes any values if they are adjacent vertically for at least (matchX) in a row
     * @return int (score accumulated)
     */
    public int sweepVertical(Board board, int matchX) {
        Tile.Color currentColor;
        Set<Integer> returnSet = new HashSet<>();
        Tile tempTile;
        int totalScore = 0;

        for (int col = 0; col < board.col; col++) {

            Set<Integer> temp = new HashSet<>();
            currentColor = board.getTile(0, col).getColor();

            for (int row = 0; row < board.row; row++) {
                tempTile = board.getTile(row, col); // Get tile

                // same color
                if (tempTile.getColor() == currentColor && currentColor != Tile.Color.NULL) {
                    temp.add(board.toIndex(row, col));
                    // Temp Big enough
                    if (temp.size() >= matchX && row == board.row - 1) {
                        returnSet.addAll(temp);
                        totalScore += (temp.size() - matchX) + 1 * SCORE_VALUE;
                    }
                }
                // not same color
                else {
                    // Temp big enough
                    if (temp.size() >= matchX) {
                        returnSet.addAll(temp);
                        totalScore += (temp.size() - matchX) + 1 * SCORE_VALUE;
                    }

                    temp = new HashSet<>();
                    temp.add(board.toIndex(row, col));
                    currentColor = tempTile.getColor();
                }
            }
        }
        for (int index : returnSet) {
            board.removeTile(index);
        }
        return totalScore;
    }

    /**
     * Provided Sweeping Match Function.
     * @param board
     * @param matchX this function removes any values if they are adjacent horizontally for at least (matchX) in a row
     * @return int (score accumulated)
     */
    public int sweepHorizontal(Board board, int matchX) {
        Tile.Color currentColor;
        Set<Integer> returnSet = new HashSet<>();
        Tile tempTile;
        int totalScore = 0;

        for (int row = 0; row < board.row; row++) {
            Set<Integer> temp = new HashSet<>();
            currentColor = board.getTile(row, 0).getColor();

            for (int col = 0; col < board.col; col++) {
                tempTile = board.getTile(row, col); // Get tile

                // same color
                if (tempTile.getColor() == currentColor && currentColor != Tile.Color.NULL) {
                    temp.add(board.toIndex(row, col));
                    // Temp Big enough
                    if (temp.size() >= matchX && col == board.col - 1) {
                        returnSet.addAll(temp);
                        totalScore += (temp.size() - matchX) + 1 * SCORE_VALUE;
                    }
                }
                // not same color
                else {
                    // Temp big enough
                    if (temp.size() >= matchX) {
                        returnSet.addAll(temp);
                        totalScore += (temp.size() - matchX) + 1 * SCORE_VALUE;
                    }
                    temp = new HashSet<>();
                    temp.add(board.toIndex(row, col));
                    currentColor = tempTile.getColor();
                }
            }
        }
        for (int index : returnSet) {
            board.removeTile(index);
        }
        return totalScore;
    }
}
