package TMGE;

import java.util.List;

public abstract class Rule {
    // // Method to find matches on the board
    // // Match class is for Matched tile
    // List<Match> findMatches(Board board);

    // // Method to handle matches (e.g., remove matched tiles, score points)
    // void handleMatches(Board board, List<Match> matches);

    // //
    abstract public boolean runAllMatch(Board board);


    // default implementation for horizontal
    public boolean matchHorizontal3(Board board) {
        // Horizontal Check
        for (int i = 0; i < board.row; i++) {
            for (int j = 0; j < board.col - 2; j++) {
                Tile.Color targetValue = board.getTile(i, j).getColor();
                //board.getTile(i,j).getColor()
                if (board.getTile(i, j).getColor() == targetValue &&
                    board.getTile(i, j + 1).getColor() == targetValue &&
                    board.getTile(i, j + 2).getColor() == targetValue){
                    return true;
                }
            }
        }
        return false; 
    }

    public boolean matchHorizontal4(Board board) {
        // Horizontal Check
        for (int i = 0; i < board.row; i++) {
            for (int j = 0; j < board.col - 3; j++) {
                Tile.Color targetValue = board.getTile(i, j).getColor();
                //board.getTile(i,j).getColor()
                if (board.getTile(i, j).getColor() == targetValue &&
                    board.getTile(i, j + 1).getColor() == targetValue &&
                    board.getTile(i, j + 2).getColor() == targetValue &&
                    board.getTile(i, j + 3).getColor() == targetValue) {
                    return true;
                }
            }
        }
        return false;
    }
}
