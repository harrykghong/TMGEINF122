package Game1;

import TMGE.Board;
import TMGE.Rule;
import TMGE.Tile;

public class Game1Rules extends Rule {


    public Game1Rules() {
        super();
    }
    
    @Override
    public int runAllMatch(Board board) {
        int total_score = 0;

        int temp = sweepVertical(board, 4);
        System.out.println("VERTICAL POINTS: " + temp);
        total_score += temp;
        temp = sweepHorizontal(board, 4);
        System.out.println("HORIZONTAL POINTS: " + temp);
        total_score += temp;


        // sweepHorizontal(board, 3); //sweep by itself will remove as much as it wants without adding score
        
        // total_score -= sweepHorizontal(board, 4); //could remove points

        // total_score += sweepHorizontal(board, 4);
        // board drops*
        // total_score -= sweepHorizontal(board, 4);    //can punish discovered sweeps(?)
        
        temp = countSingular(board);
        System.out.println("SINGLE POINTS: " + temp);
        total_score += temp;

        return total_score;
    }
    
    private boolean checkIsolated(Board board, int row, int col){
        
        Tile currentTile = board.getTile(row, col);
        Tile temp;

        try {
            temp = board.getTile(row-1, col);
            if (temp.getColor() == currentTile.getColor()) return false;

        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            temp = board.getTile(row+1, col);
            if (temp.getColor() == currentTile.getColor()) return false;

        } catch (ArrayIndexOutOfBoundsException e) {}
        
        try {
            temp = board.getTile(row, col-1);
            if (temp.getColor() == currentTile.getColor()) return false;

        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            temp = board.getTile(row, col+1);
            if (temp.getColor() == currentTile.getColor()) return false;

        } catch (ArrayIndexOutOfBoundsException e) {}

        return true;
    }

    private int countSingular(Board board) {
        int singles = 0;
        for (int currentRow = 0; currentRow < board.row; currentRow++) {
            for (int currentCol = 0; currentCol < board.col; currentCol++) {
                boolean isIsolated = checkIsolated(board, currentRow, currentCol);
                if (isIsolated) {
                    singles++;
                }
            }
        }
        
        return -5 * singles;
    }

    @Override
    public boolean checkGameOver(Board board, int turn, int score) {


        // Idea: get over a certan point threshold based on turn number; ie 1000 points by turn 5, 2500 by turn 10, etc
        // Idea: subtract [some amount] points from current points every turn. if you go negtive you lose
        // ide: must match horizonntal, then verticle, then horizontal... if fail to do so lose
        
        int targetpoint = turn * 200;
        if (turn % 5 == 0){
            if (score < targetpoint) return true;
        }
        
        return false;
    }
}
