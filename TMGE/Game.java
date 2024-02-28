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
    private List<String> mathingRules;
    
    // Testing Additional Members (Matthew)
    private GUI workingGUI;
    private int numberOfPlayers;

    // Matthew's Game
    public Game(GUI gui, Board template, int numberOfPlayers) {
        this.workingGUI = gui;
        this.numberOfPlayers = numberOfPlayers;

        this.boardList = new ArrayList<Board>();
        for (int i = 0; i < this.numberOfPlayers; i++) {
            this.boardList.add(new Board(template));
        }

        this.currentPlayerIndex = 0;

        this.userManager = UserManager.getInstance();
        for (int i = 0; i < this.numberOfPlayers; i++) {
            this.userManager.addUser(this.workingGUI.getInput());
        }

        this.isRunning = true;
    }

    // Matthew's run
    public void runTEST() {
        while (true) {
            String nextInput = workingGUI.getInput();

            while(!processInput(nextInput, this.currentPlayerIndex)) {
                workingGUI.printToScreen("Player " + (this.currentPlayerIndex + 1) + "'s input is wrong!");
            }

            updateGameState(this.currentPlayerIndex);

            renderTest();
        }
    }

    public void renderTest() {
        this.workingGUI.printToScreen(this.userManager);
    }

	public Game(int numberOfPlayers) {
        boardList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            boardList.add(new Board(4, 4)); // Assuming all boards are 4x4 for simplicity
        }
        currentPlayerIndex = 0;
        this.userManager = UserManager.getInstance();
        this.isRunning = true; // Ensure the game loop can start
        mathingRules = new ArrayList<>();
    }
	
    public void run() {

		// we should process one player at a time.
        while (isRunning) {
            System.out.println("Player " + (currentPlayerIndex + 1) + "'s turn:");

            //Keep scanner input for now, keylistener is for GUI b
			String input = scanner.nextLine();
            
            while(!processInput(input, currentPlayerIndex)){
                System.out.println("Player " + (currentPlayerIndex + 1) + "'s input is wrong!");
            }
            
            //do the match check on board
            updateGameState(currentPlayerIndex); // Update the game state based on rules

            render(); // In a non-GUI context, print the state to the console

            currentPlayerIndex = (currentPlayerIndex + 1) % boardList.size();
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

    private boolean processInput(String input, int currentPlayer) {
        // Process player input or simulate game actions
        // w s a d to control the selector in board, r to confirm the selection
        Board currentBoard = boardList.get(currentPlayerIndex);
        switch(input){
            case "w":
                return currentBoard.moveSelectorUp();
            case "s":
                return currentBoard.moveSelectorDown();
            case "a":
                return currentBoard.moveSelectorLeft();
            case "d":
                return currentBoard.moveSelectorRight();
            case "f":
                // select tile
                return currentBoard.selectorSelects();
            case "r":
                // do swap
                return currentBoard.selectorSwap();
            default:
                return false;
        }
    }

    private void updateGameState(int currentPlayerIndex) {
        //Apply match rules here
        // Update your game board and game state here

        String rule =  "match 3 horizontal";
        String rule2 =  "match 3 colors";
        mathingRules.add(rule);
        mathingRules.add(rule2);
        boardList.get(currentPlayerIndex).update(mathingRules);
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
