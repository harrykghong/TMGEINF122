package DemoAnt;

import java.util.ArrayList;

import TMGE.Board;
import TMGE.GUI;
import TMGE.Game;
import TMGE.Rule;
import TMGE.Spawner;
import TMGE.TerminalOutputGUI;
import TMGE.Tile;

public class DemoAnt {
    public DemoAnt(GUI gui, int numPlayers) {
        Board template = new Board(4, 4);
        
        ArrayList<Tile> tileSet = new ArrayList<Tile>();
        tileSet.add(new Tile(1));
        tileSet.add(new Tile(2));
        tileSet.add(new Tile(3));
        tileSet.add(new Tile(4));
        
        Rule rules = new DemoAntRules();
    
        Game game = new Game(gui, template, numPlayers, rules, new Spawner(tileSet));
        game.run();
        
    }
}
