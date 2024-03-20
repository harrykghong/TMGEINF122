package TestingPackage;

import TMGE.*;
//import TMGE.GameFrame;
import java.util.ArrayList;

public class TestingGUI {
    private static void run() {

        Board template = new Board(4, 4);
        Board template2 = new Board(4, 4);
        ArrayList<Board> boardList = new ArrayList<>();
        boardList.add(template);
        boardList.add(template2);

        GameFrame workingGUI = new GameFrame(boardList);
        
        int numPlayers = 2; //Integer.parseInt(workingGUI.getInput("How many players are there? ")); //missing function
        TestingRules ruleSet = new TestingRules();

        Game game = new Game(workingGUI, template, numPlayers, ruleSet);
        game.run();
    }

    public static void main(String[] args) {
        run();
    }
}
