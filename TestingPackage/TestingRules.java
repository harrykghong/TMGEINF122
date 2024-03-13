package TestingPackage;

import TMGE.Rule;
import TMGE.Board;

public class TestingRules extends Rule {
    
    public TestingRules() {
        super();
    }
    
    @Override
    public int runAllMatch(Board board) {
        System.out.println("int runAllMatch");
        int score = 0;
        score += sweepHorizontal(board, 3);
        score += 0.5 * sweepVertical(board, 3);
        board.postMatch();
        return score;
    }
}
