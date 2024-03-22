package TMGE;

import Bejeweled.Bejeweled;
import DemoAnt.DemoAnt;

public class Main {
    public static void main(String[] args) {
        GUI gui = TerminalOutputGUI.getInstance();
        UserManager manager = UserManager.getInstance();
        
        int numberOfPlayers = Integer.parseInt(gui.getInput("Number of Players"));
        
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = gui.getInput("Input Username: ");
            manager.addUser(name);
        }


        while(true)
        {
            String gameName = gui.getInput("What game do you want to play? Bejeweled or DemoAnt? Enter \"none\" to close");
            
            if (gameName.toLowerCase().equals("none")) {
                break;
            }

            switch (gameName.toLowerCase()) {
                case "bejeweled":
                    new Bejeweled(gui, numberOfPlayers);
                    break;
                case "demoant":
                    new DemoAnt(gui, numberOfPlayers);
                    break;
                default:
                    gui.printToScreen("Invalid game name, try again");
            }
            manager.clearScores();
        }
    }
}
