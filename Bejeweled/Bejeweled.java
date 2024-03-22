package Bejeweled;

import TMGE.Board;
import TMGE.GUI;
import TMGE.TerminalOutputGUI;
import TMGE.Rule;
import TMGE.Game;

public class Bejeweled {
    public Bejeweled(GUI gui, int numPlayers) {
        Board template = new Board(5,5);
        Rule rules = new BejeweledRules();
        Game game = new BejeweledGame(gui, template, numPlayers, rules);
        game.run();
    }
}