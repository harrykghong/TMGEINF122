package TestingPackage;

import TMGE.Rule;
import TMGE.Board;

public class TestingRules extends Rule {
    
    public TestingRules() {
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
        if (score > 200) {
            return true;
        }

        return false;
    }
}
