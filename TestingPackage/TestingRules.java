package TestingPackage;

import TMGE.Rule;
import TMGE.Board;

public class TestingRules extends Rule {
    
    public TestingRules() {
        super();
    }
    
    @Override
    public boolean runAllMatch(Board board) {
        matchVertical(board);
        matchHorizontal4(board);
        return true;
    }

    private boolean matchVertical(Board board) {
        return false;
    }
}
