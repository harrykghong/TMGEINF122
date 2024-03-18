package Bejeweled;

import TMGE.Board;
import TMGE.GUI;
import TMGE.TerminalOutputGUI;
import TMGE.Rule;
import TMGE.Game;
import TestingPackage.TestingRules;

public class Bejeweled {
    private static void run(){
        GUI gui = TerminalOutputGUI.getInstance();
        Board template = new Board(8, 8);
        int numPlayers = Integer.parseInt(gui.getInput("Number of Players"));
        Rule rules = new BejeweledRules();

        Game game = new BejeweledGame(gui, template, numPlayers, rules);
        game.runTEST();
    }
    
    public static void main(String[] args) {
        run();
    }
}