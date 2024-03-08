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
        score += matchVertical(board);
        score += matchHorizontal4(board);
        score += sweepVertical(board, 3);
        return score;
    }

    private int matchVertical(Board board) {
        return 0;
    }
}
