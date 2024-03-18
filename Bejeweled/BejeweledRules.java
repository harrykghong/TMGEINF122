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
    public boolean checkGameOver(Board board, int turn, int score) {
        if (score > 0) {
            return true;
        }

        if (turn > 3) {
            return true;
        }

        return false;
    }
}
