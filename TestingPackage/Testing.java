package TestingPackage;

import TMGE.*;

public class Testing {
    private static void run() {
        GUI workingGUI = TerminalOutputGUI.getInstance();
        Board template = new Board(4, 4);
        int numPlayers = Integer.parseInt(workingGUI.getInput());
        TestingRules ruleSet = new TestingRules();

        // Game game = new Game(workingGUI, template, )
    }

    public static void main(String[] args) {
        run();
    }
}
