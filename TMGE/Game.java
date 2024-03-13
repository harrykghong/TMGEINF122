package TMGE;
import java.util.ArrayList;
// Testing Game Class
/*
	The main gameloop
*/
import java.util.List;

import java.util.Scanner;

public class Game {
	private List<Board> boardList;
	private boolean isRunning;
	private UserManager userManager;
    private int currentPlayerIndex;
    private static final Scanner scanner = new Scanner(System.in);
    private Rule rule;
    
    // Testing Additional Members (Matthew)
    private GUI workingGUI;
    private int numberOfPlayers;
    private ArrayList<String> users;
    private Spawner spawner;

    // Testing GUI (GameFrame)
    private GameFrame gameFrame;

    // Matthew's Game
    public Game(GUI gui, Board template, int numberOfPlayers, Rule ruleSet) {
        this.workingGUI = gui;
        this.numberOfPlayers = numberOfPlayers;

        this.boardList = new ArrayList<Board>();
        // this.gameFrame = new GameFrame(this.boardList);
        this.users = new ArrayList<String>();

        this.currentPlayerIndex = 0;

        this.userManager = UserManager.getInstance();

        this.spawner = new Spawner();
        for (int i = 0; i < this.numberOfPlayers; i++) {
            String name = this.workingGUI.getInput("Input Username: ");
            this.userManager.addUser(name);
            this.users.add(name);

            this.boardList.add(new Board(template));
            for (Board someBoard: this.boardList){
                this.spawner.fill(someBoard);
            }

        }

        this.isRunning = true;
        this.rule = ruleSet;
    }

    // Matthew's run
    public void runTEST() {
        while (true) {
            renderBoard(this.currentPlayerIndex);
            String nextInput = workingGUI.getInput("Make your Move: ");

            try {
                while(!processInput(nextInput, this.currentPlayerIndex)) {
                    // workingGUI.printToScreen("Player " + (this.currentPlayerIndex + 1) + "'s input is wrong!");
                    renderBoard(this.currentPlayerIndex);
                    nextInput = workingGUI.getInput("Make your Move: ");
                }
            }
            catch (Exception exception) {
                workingGUI.printToScreen("Player " + (this.currentPlayerIndex + 1) + "'s input is wrong!");
                continue;
            }

            System.out.println("Done processing input! Doing matching rule check");
            updateGameState(this.currentPlayerIndex);
                                  
            renderBoard(this.currentPlayerIndex);

            if (this.workingGUI.getInput("Enter to Continue or 'q' to quit: ").equals("q")) {
                break;
            }
            
            //this.workingGUI.printToScreen(">>>>>"+currentPlayerIndex + " " + boardList.size());
            currentPlayerIndex = (currentPlayerIndex + 1) % boardList.size();
        }
    }

    public void renderBoard(int playerIndex) {
        this.workingGUI.printToScreen("User " + this.users.get(playerIndex));
        this.workingGUI.printToScreen(this.boardList.get(playerIndex));

    }

	public Game(int numberOfPlayers) {
        boardList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            boardList.add(new Board(4, 4)); // Assuming all boards are 4x4 for simplicity
        }
        currentPlayerIndex = 0;
        this.userManager = UserManager.getInstance();
        this.isRunning = true; // Ensure the game loop can start
        // mathingRules = new ArrayList<>();
    }
	
    public void run() {

		// we should process one player at a time.
        while (isRunning) {
            System.out.println("Player " + (currentPlayerIndex + 1) + "'s turn:");

            //Keep scanner input for now, keylistener is for GUI b
			String input = scanner.nextLine();
            
            // while(!processInput(input, currentPlayerIndex)){
            //     System.out.println("Player " + (currentPlayerIndex + 1) + "'s input is wrong!");
            // }
            
            //do the match check on board
            updateGameState(currentPlayerIndex); // Update the game state based on rules

            render(); // In a non-GUI context, print the state to the console

            // Implement some delay if necessary to control game speed
            try {
                Thread.sleep(100); // Sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Condition to exit the loop/game, e.g., when the game is over
            if (gameOverCondition()) {
                isRunning = false;
            }
        }

        cleanup();
    }

    private boolean processInput(String input, int currentPlayer) throws Exception {
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

    private void updateGameState(int currentPlayerIndex) {
        // Rule Class added version
        System.out.println("Updating Game State for P"+currentPlayerIndex);
        Board currentBoard = boardList.get(currentPlayerIndex);
        int addscore = rule.runAllMatch(currentBoard);
        System.out.println(addscore);
        userManager.getUser(users.get(currentPlayerIndex)).addScore(addscore);
    }

    private void render() {
        for(int i = 0; i<currentPlayerIndex; i++){
            System.out.println("Player" + (currentPlayerIndex+1) + "Board:");
		    System.out.println(boardList.get(i+1).toString());
        }
    }

    private boolean gameOverCondition() {
        // Implement logic to determine if the game is over
        return false; // Placeholder
    }

    private void cleanup() {
        // Perform any cleanup if necessary
        System.out.println("Game Over");
    }
}
