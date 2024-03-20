package TestingPackage;

import TMGE.*;

public class Testing {
    private static void run() {
        GUI workingGUI = TerminalOutputGUI.getInstance();
        Board template = new Board(4, 4);
        int numPlayers = Integer.parseInt(workingGUI.getInput("How many players are there? "));
        TestingRules ruleSet = new TestingRules();

        Game game = new Game(workingGUI, template, numPlayers, ruleSet);
        game.run();
    }

    public static void main(String[] args) {
        run();
    }
}
