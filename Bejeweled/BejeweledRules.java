package Bejeweled;

import TMGE.Rule;
import TMGE.Board;

public class BejeweledRules extends Rule {
    
    public BejeweledRules() {
        super();
    }
    
    @Override
    public int runAllMatch(Board board) {
        int score = 0;
        score += sweepHorizontal(board, 3);
        score += sweepVertical(board, 3);
        return score;
    }

    @Override
    public boolean checkGameOver(Board board, int turn_number, int score) {
        if (score > 500) {
            return true;
        }

        return false;
    }
}
