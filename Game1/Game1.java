package Game1;

import TMGE.Board;
import TMGE.GUI;
import TMGE.Game;
import TMGE.Rule;
import TMGE.TerminalOutputGUI;

public class Game1 {
    private static void run() {
        GUI gui = TerminalOutputGUI.getInstance();
        Board template = new Board(8, 8);
        int numPlayers = Integer.parseInt(gui.getInput("Number of Players"));
        Rule rules = new Game1Rules();
    
        Game game = new Game(gui, template, numPlayers, rules);
        game.runTEST();
    }

    public static void main(String[] args) {
        run();
    }
}
