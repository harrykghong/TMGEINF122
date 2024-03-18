package Game1;

import TMGE.Board;
import TMGE.Rule;

public class Game1Rules extends Rule {
    @Override
    public int runAllMatch(Board board) {
        return 0;
    }

    @Override
    public boolean checkGameOver(Board board, int turn, int score) {
        return false;
    }
}
