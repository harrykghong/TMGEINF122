package TMGE;
import java.util.ArrayList;
// Testing Game Class
/*
	The main gameloop
*/
import java.util.List;

public class Game {
	public List<Board> boardList;
	public UserManager userManager;
    public Rule rule;
    public ArrayList<Boolean> gameovers;
    
    // Testing Additional Members (Matthew)
    public GUI workingGUI;
    public int numberOfPlayers;
    public ArrayList<String> users;
    public Spawner spawner;
    public boolean doesFill = true;

    // Testing GUI (GameFrame)
    public GameFrame gameFrame;

    // Matthew's Game
    public Game(GUI gui, Board template, int numberOfPlayers, Rule rule) {
        this.workingGUI = gui;
        this.numberOfPlayers = numberOfPlayers;

        this.boardList = new ArrayList<Board>();
        // this.gameFrame = new GameFrame(this.boardList);
        this.users = new ArrayList<String>();
        this.gameovers = new ArrayList<Boolean>();

        // this.currentPlayerIndex = 0;

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


            // int[][] tempArray = {
            //             {1, 2, 3, 5},
            //             {1, 5, 6, 6},
            //             {1, 6, 3, 5},
            //             {1, 2, 3, 5}
            //         };

            // this.boardList.add(new Board(tempArray));

        }

        //this.isRunning = true;
        this.rule = rule;
    }

    public Game(GUI gui, Board template, int numberOfPlayers, Rule ruleSet, boolean doesFill) {
        this(gui, template, numberOfPlayers, ruleSet);
        this.doesFill = doesFill;
    }

    // Matthew's run
    public void runTEST() {
        int x = 0; //this is temp, i wanted to debug
        int turn_counter = 1;
        System.out.println("'Before loop'");
        while (!isGameOver()) {
            System.out.println("'AFter loop'");
            for (int currentPlayerIndex = 0; currentPlayerIndex < this.numberOfPlayers; currentPlayerIndex++) {
                if (this.gameovers.get(currentPlayerIndex)) {
                    continue;
                }

                renderBoard(currentPlayerIndex);
                String nextInput = workingGUI.getInput("Turn#" + turn_counter + " Make your Move: ");

                try {
                    while(!processInput(nextInput, currentPlayerIndex)) {
                        renderBoard(currentPlayerIndex);
                        nextInput = workingGUI.getInput("Player#"+currentPlayerIndex+", make your move: " + " INPUT#" + (x++) + " TURN#" + turn_counter);
                    }
                } 
                catch (Exception exception) {
                    workingGUI.printToScreen("Player " + (currentPlayerIndex + 1) + "'s input is wrong!");
                    continue;
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
                if (this.workingGUI.getInput("Enter to Continue or 'q' to quit: ").equals("q")) {
                    break;
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
        
        this.workingGUI.printToScreen("User " + this.users.get(playerIndex) + " Score = " + getPlayerScore(playerIndex));
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
                //check boolean here, bejeweled does not allow and i think should halt here

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
        currentBoard.dropTiles();

        if (this.doesFill) { this.spawner.fill(currentBoard); };
        userManager.getUser(users.get(currentPlayerIndex)).addScore(addscore);
    }
}
