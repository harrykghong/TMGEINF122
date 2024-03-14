package TMGE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Rule {
    private static final int SCORE_VALUE = 100;
    // // Method to find matches on the board
    // // Match class is for Matched tile
    // List<Match> findMatches(Board board);

    // // Method to handle matches (e.g., remove matched tiles, score points)
    // void handleMatches(Board board, List<Match> matches);

    // //
    abstract public int runAllMatch(Board board);

    public int sweepVertical(Board board, int matchX){
        System.out.println("in sweepVertical");
        Tile.Color currentColor;
        Set<Integer> returnSet = new HashSet<>();
        Tile tempTile;
        int totalScore = 0;

        for (int col = 0; col < board.col; col++) {
            
            Set<Integer> temp = new HashSet<>();
            currentColor = board.getTile(0, col).getColor();
            
            for (int row = 0; row < board.row; row++) {
                tempTile = board.getTile(row, col);	//Get tile

                // same color
                if (tempTile.getColor() == currentColor && currentColor != Tile.Color.NULL) {
                    temp.add(board.getIndex(row, col));
                    // Temp Big enough
                    if (temp.size() >= matchX && row == board.row-1) {
                        returnSet.addAll(temp);
                        totalScore += (temp.size() - matchX) + 1 * SCORE_VALUE;
                    }
                }
                // not same color
                else
                {
                    // Temp big enough
                    if (temp.size() >= matchX) {
                        returnSet.addAll(temp);
                        totalScore += (temp.size() - matchX) + 1 * SCORE_VALUE;
                    }

                    temp = new HashSet<>();
                    temp.add(board.getIndex(row, col));
                    currentColor = tempTile.getColor();
                }
            }
            // System.out.println("remove these indexes:" + returnSet);
        }
        // System.out.println("remove these indexes:" + returnSet);
        System.out.println("REMOVE " + returnSet);
        for (int index: returnSet){
            board.removeTile(index);
        }
        return totalScore;
    }

    public int sweepHorizontal(Board board, int matchX){
        System.out.println("in sweepHorizontal");
        Tile.Color currentColor;
        Set<Integer> returnSet = new HashSet<>();
        Tile tempTile;
        int totalScore = 0;

        for (int row = 0; row < board.row; row++) {
            Set<Integer> temp = new HashSet<>();
            currentColor = board.getTile(row, 0).getColor();
            for (int col = 0; col < board.col; col++) {
                tempTile = board.getTile(row, col);	//Get tile
                System.out.println("tempTile:"+tempTile);
                // same color
                if (tempTile.getColor() == currentColor && currentColor != Tile.Color.NULL) {
                    temp.add(board.getIndex(row, col));
                    // Temp Big enough
                    if (temp.size() >= matchX && row == board.row-1) {
                        returnSet.addAll(temp);
                    }
                }
                // not same color
                else
                {
                    // Temp big enough
                    if (temp.size() >= matchX) {
                        returnSet.addAll(temp);
                    }
                    temp = new HashSet<>();
                    temp.add(board.getIndex(row, col));
                    currentColor = tempTile.getColor();
                }
            }
        }
        System.out.println("REMOVE " + returnSet);
        for (int index: returnSet){
            board.removeTile(index);
        }
        return totalScore;
    }


    
    // default implementation for horizontal
    // public int matchHorizontal3(Board board) {
    //     // Horizontal Check
    //     for (int i = 0; i < board.row; i++) {
    //         for (int j = 0; j < board.col - 2; j++) {
    //             Tile.Color targetValue = board.getTile(i, j).getColor();
    //             //board.getTile(i,j).getColor()
    //             if (board.getTile(i, j).getColor() == targetValue &&
    //                 board.getTile(i, j + 1).getColor() == targetValue &&
    //                 board.getTile(i, j + 2).getColor() == targetValue){
    //                 return 100;
    //             }
    //         }
    //     }
    //     return 0; 
    // }

    // public int matchHorizontal4(Board board) {
    //     // Horizontal Check
    //     for (int i = 0; i < board.row; i++) {
    //         for (int j = 0; j < board.col - 3; j++) {
    //             Tile.Color targetValue = board.getTile(i, j).getColor();
    //             //board.getTile(i,j).getColor()
    //             if (board.getTile(i, j).getColor() == targetValue &&
    //                 board.getTile(i, j + 1).getColor() == targetValue &&
    //                 board.getTile(i, j + 2).getColor() == targetValue &&
    //                 board.getTile(i, j + 3).getColor() == targetValue) {
    //                 return 200;
    //             }
    //         }
    //     }
    //     return 0;
    // }
}
