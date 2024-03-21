package TMGE;
import java.util.ArrayList;
// Testing Game Class
/*
	The main gameloop
*/
import java.util.List;

public class Game {
    public int numberOfPlayers;
	public List<Board> boardList;
	public UserManager userManager;
    public ArrayList<Boolean> gameovers;
    public ArrayList<String> users;
    
    public GUI workingGUI;
    public GameFrame GUI;
    
    public Spawner spawner;
    public Rule rule;

    public GameFrame gameFrame;

    // Main Constructor
    public Game(GUI gui, Board template, int numberOfPlayers, Rule rule, Spawner spawner) {
        this.workingGUI = gui;
        this.numberOfPlayers = numberOfPlayers;

        this.boardList = new ArrayList<Board>();
        this.users = new ArrayList<String>();
        this.gameovers = new ArrayList<Boolean>();

        this.userManager = UserManager.getInstance();

        this.spawner = spawner;
        for (int i = 0; i < this.numberOfPlayers; i++) {
            String name = this.workingGUI.getInput("Input Username: ");
            this.userManager.addUser(name);
            this.users.add(name);
            this.gameovers.add(false);

            this.boardList.add(new Board(template));
            for (Board someBoard: this.boardList){
                this.spawner.fill(someBoard);
            }
        }

        //this.isRunning = true;
        this.rule = rule;
    }


    // version with GUI
    public Game(GameFrame GUI, Board template, int numberOfPlayers, Rule rule) {
        this.GUI = GUI;
        this.numberOfPlayers = numberOfPlayers;

        this.boardList = new ArrayList<Board>();
        // this.gameFrame = new GameFrame(this.boardList);
        this.users = new ArrayList<String>();
        this.gameovers = new ArrayList<Boolean>();

        this.userManager = UserManager.getInstance();

        this.spawner = new Spawner();
        for (int i = 0; i < this.numberOfPlayers; i++) {
            String name = this.workingGUI.getInput("Input Username: ");
            this.userManager.addUser(name);
            this.users.add(name);
            this.gameovers.add(false);

            this.boardList.add(new Board(template));
            for (Board someBoard: this.boardList){
                this.spawner.fill(someBoard);
            }

        }
        //this.isRunning = true;
        this.rule = rule;
    }

    public Game(GUI gui, Board template, int numberOfPlayers, Rule ruleSet) {
        this(gui, template, numberOfPlayers, ruleSet, new Spawner());
    }

    // Matthew's run
    public void run() {
        int turn_counter = 1;
        while (!isGameOver()) {
            for (int currentPlayerIndex = 0; currentPlayerIndex < this.numberOfPlayers; currentPlayerIndex++) {
                if (this.gameovers.get(currentPlayerIndex)) {
                    continue;
                }

                this.workingGUI.getInput("Player " + this.users.get(currentPlayerIndex) + "'s turn! Enter to Continue");

                renderBoard(currentPlayerIndex);
                String nextInput = workingGUI.getInput("Turn #" + turn_counter + " Make your Move: ");

                //Game halts while it waits for input
                //ideally the gameframe shoudl provide that input
                //button press = pass some string here
                //but it doesn't have a getIput function

                // try {
                //     while(!processInput(nextInput, currentPlayerIndex)) {
                //         renderBoard(currentPlayerIndex);
                //         nextInput = workingGUI.getInput("Turn #" + turn_counter + " Make your Move: ");
                //     }
                // } 
                // catch (Exception exception) {
                //     // workingGUI.printToScreen("Player " + (currentPlayerIndex + 1) + "'s input is wrong!");
                //     nextInput = workingGUI.getInput("Input '" + nextInput + "' was invalid, please try again: ");
                //     continue;
                // }
                
                // nextInput = workingGUI.getInput("Turn #" + turn_counter + " Make your Move: ");
                while(true) {
                    try {
                        if (processInput(nextInput, currentPlayerIndex)) {
                            break;
                        }
                        
                        renderBoard(currentPlayerIndex);
                        nextInput = workingGUI.getInput("Turn #" + turn_counter + " Make your Move: ");
                    } 
                    catch (Exception exception) {
                        // workingGUI.printToScreen("Player " + (currentPlayerIndex + 1) + "'s input is wrong!");
                        nextInput = workingGUI.getInput("Input '" + nextInput + "' was invalid, please try again: ");
                    }
                }
                

                updateGameState(currentPlayerIndex);

                Board someBoard = boardList.get(currentPlayerIndex);
                int score = getPlayerScore(currentPlayerIndex);
                if (this.rule.checkGameOver(someBoard, turn_counter, score)) {
                    this.workingGUI.printToScreen("GAME OVER!");
                    this.gameovers.set(currentPlayerIndex, true);
                }

                renderBoard(currentPlayerIndex);

                //WHy is this q to quit just hanging out by itself instead of under process input?
                if (this.workingGUI.getInput("Enter to Continue or 'q' to gameover: ").equals("q")) {
                    this.gameovers.set(currentPlayerIndex, true);
                }
                
            }
            turn_counter++;
        }
    }

    private boolean isGameOver() {
        //return this.gameovers.stream().allMatch(Boolean::booleanValue);

        for (boolean playerState : this.gameovers) {
            if (!playerState) {
                return false;
            }
        }
        return true;
    }

    public void renderBoard(int playerIndex) {
        
        this.workingGUI.printToScreen("\nUser " + this.users.get(playerIndex) + " Score = " + getPlayerScore(playerIndex));
        this.workingGUI.printToScreen(this.boardList.get(playerIndex));

    }

    public int getPlayerScore(int currentPlayerIndex) {
        return this.userManager.getUser(this.users.get(currentPlayerIndex)).getUserScore();
    }
	
    public boolean processInput(String input, int currentPlayerIndex) throws Exception {
        // Process player input or simulate game actions

        // WASD Controls
        // F to select
        // R to swap
        // w s a d to control the selector in board, r to confirm the selection
        Board currentBoard = boardList.get(currentPlayerIndex);
        switch(input){
            case "w":
                currentBoard.moveSelectorUp();
                return false;
            case "s":
                currentBoard.moveSelectorDown();
                return false;
            case "a":
                currentBoard.moveSelectorLeft();
                return false;
            case "d":
                currentBoard.moveSelectorRight();
                return false;
            case "f":
                currentBoard.selectorSelects();
                return false;
            case "r":
                return currentBoard.selectorSwap();
                // Do not put "return false" here, it is the condition to progress turns
            default:
                throw new Exception();
                // return false;
        }
    }

    public void updateGameState(int currentPlayerIndex) {
        // Rule Class added version
        Board currentBoard = boardList.get(currentPlayerIndex);
        int addscore = rule.runAllMatch(currentBoard);
        //System.out.println("addscore="+addscore);

        renderBoard(currentPlayerIndex);

        currentBoard.dropTiles();

        this.workingGUI.getInput("Continue");
        renderBoard(currentPlayerIndex);

        this.spawner.fill(currentBoard);

        
        this.workingGUI.getInput("Continue");
        renderBoard(currentPlayerIndex);

        userManager.getUser(users.get(currentPlayerIndex)).addScore(addscore);
    }
}
