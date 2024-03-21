package Bejeweled;

import TMGE.Board;
import TMGE.GUI;
import TMGE.TerminalOutputGUI;
import TMGE.Rule;
import TMGE.Game;

public class Bejeweled {
    private static void run(){
        GUI gui = TerminalOutputGUI.getInstance();
        Board template = new Board(5,5);
        int numPlayers = Integer.parseInt(gui.getInput("Number of Players"));
        Rule rules = new BejeweledRules();

        Game game = new BejeweledGame(gui, template, numPlayers, rules);
        game.run();
    }
    
    public static void main(String[] args) {
        run();
    }
}