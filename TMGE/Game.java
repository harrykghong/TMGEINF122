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

            // this.boardList.add(new Board(template));
            // for (Board someBoard: this.boardList){
            //     this.spawner.fill(someBoard);
            // }


            int[][] tempArray = {
                        {1, 2, 3, 4}, 
                        {5, 5, 5, 5}, 
                        {1, 2, 3, 4},
                        {1, 2, 3, 4} 
                    };

            this.boardList.add(new Board(tempArray));

        }

        this.isRunning = true;
        this.rule = ruleSet;
    }

    // Matthew's run
    public void runTEST() {
        int x = 0;
        while (true) {
            renderBoard(this.currentPlayerIndex);
            String nextInput = workingGUI.getInput("Make your Move: ");

            try {
                while(!processInput(nextInput, this.currentPlayerIndex)) {
                    renderBoard(this.currentPlayerIndex);
                    nextInput = workingGUI.getInput("Make your Move: "+" TURN#"+x++);
                }
            }
            catch (Exception exception) {
                workingGUI.printToScreen("Player " + (this.currentPlayerIndex + 1) + "'s input is wrong!");
                continue;
            }

            updateGameState(this.currentPlayerIndex);

            //public boolean checkGameOver(Board board, int turn, int score)
            if (this.rule.checkGameOver(boardList.get(currentPlayerIndex), 5, getPlayerScore(currentPlayerIndex))) {
                this.workingGUI.printToScreen("GAME OVER!");

                //Should the program terminate? does the loop continue?
            }

            renderBoard(this.currentPlayerIndex);


            //WHy is this q to quit just hanging out by itself instead of under process input?
            if (this.workingGUI.getInput("Enter to Continue or 'q' to quit: ").equals("q")) {
                break;
            }

            currentPlayerIndex++;
            currentPlayerIndex %= boardList.size();
            //currentPlayerIndex = (currentPlayerIndex + 1) % boardList.size();
        }
    }

    public void renderBoard(int playerIndex) {
        
        this.workingGUI.printToScreen("User " + this.users.get(playerIndex) + " Score = " + getPlayerScore(playerIndex));
        this.workingGUI.printToScreen(this.boardList.get(playerIndex));

    }

    public int getPlayerScore(int currentPlayerIndex) {
        return this.userManager.getUser(this.users.get(currentPlayerIndex)).getUserScore();
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
        Board currentBoard = boardList.get(currentPlayerIndex);
        int addscore = rule.runAllMatch(currentBoard);
        currentBoard.dropTiles();
        this.spawner.fill(currentBoard);
        userManager.getUser(users.get(currentPlayerIndex)).addScore(addscore);
        
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
