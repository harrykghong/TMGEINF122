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

    public Spawner spawner;
    public Rule rule;

    // Main Constructor
    /**
     * Will generate a game with given parameters
     */
    public Game(GUI gui, Board template, int numberOfPlayers, Rule rule, Spawner spawner) {
        this.workingGUI = gui;
        this.numberOfPlayers = numberOfPlayers;

        this.boardList = new ArrayList<Board>();
        this.users = new ArrayList<String>();
        this.gameovers = new ArrayList<Boolean>();
        this.userManager = UserManager.getInstance();

        this.spawner = spawner;
        for (int i = 0; i < this.numberOfPlayers; i++) {
            this.users = this.userManager.getUsers();
            this.gameovers.add(false);

            this.boardList.add(new Board(template));
            for (Board someBoard : this.boardList) {
                this.spawner.fill(someBoard);
            }
        }

        this.rule = rule;
    }

    // Sub-constructor
    /**
     * Will generate a game with the defaulted spawner object of tiles 1-9
     */
    public Game(GUI gui, Board template, int numberOfPlayers, Rule ruleSet) {
        this(gui, template, numberOfPlayers, ruleSet, new Spawner());
    }

    // Main game loop
    /**
     * Will act as the main run loop for game. When called, will iterate through all
     * players and process the games individually
     * 
     */
    public void run() {

        int turn_counter = 1;
        while (!isGameOver()) {
            for (int currentPlayerIndex = 0; currentPlayerIndex < this.numberOfPlayers; currentPlayerIndex++) {
                // If player has gameover-ed, will skip that player's turn
                if (this.gameovers.get(currentPlayerIndex)) {
                    continue;
                }

                // Announces next turn
                this.workingGUI.getInput("Player " + this.users.get(currentPlayerIndex) + "'s turn! Enter to Continue");

                renderBoard(currentPlayerIndex);
                String nextInput = workingGUI.getInput("Turn #" + turn_counter + " Make your Move: ");

                // Reads input until a sucessful input is read, AND the input is turn ending.
                while (true) {
                    try {
                        if (processInput(nextInput, currentPlayerIndex)) {
                            break;
                        }

                        renderBoard(currentPlayerIndex);
                        nextInput = workingGUI.getInput("Turn #" + turn_counter + " Make your Move: ");
                    } catch (Exception exception) {
                        nextInput = workingGUI.getInput("Input '" + nextInput + "' was invalid, please try again: ");
                    }
                }

                // Update Sequence (Drop, spawn, etc)
                updateGameState(currentPlayerIndex);

                // Gameover checking, sends data to rule to check if gameover
                Board someBoard = boardList.get(currentPlayerIndex);
                int score = getPlayerScore(currentPlayerIndex);
                if (this.rule.checkGameOver(someBoard, turn_counter, score)) {
                    this.workingGUI.printToScreen("GAME OVER!");
                    this.gameovers.set(currentPlayerIndex, true);
                }

                renderBoard(currentPlayerIndex);

                // Ending confirmation before next player
                if (this.workingGUI.getInput("Enter to Continue or 'q' to gameover: ").equals("q")) {
                    this.gameovers.set(currentPlayerIndex, true);
                }
            }

            turn_counter++;
        }
    }

    // Gameover checking
    /**
     * Will iterate over all gameover states to see if all games have ended
     * 
     * @return this function returns a boolean representing whether all players are
     *         deactivated
     */
    private boolean isGameOver() {
        for (boolean playerState : this.gameovers) {
            if (!playerState) {
                return false;
            }
        }
        return true;
    }

    // Renders Board for GUI
    /**
     * Will provide the printing information to be used in the working GUI.
     * 
     * @param playerIndex this function expects a index representing the player (by
     *                    order of creation)
     */
    public void renderBoard(int playerIndex) {
        this.workingGUI
                .printToScreen("\nUser " + this.users.get(playerIndex) + " Score = " + getPlayerScore(playerIndex));
        this.workingGUI.printToScreen(this.boardList.get(playerIndex));
    }

    // Method to get a players score
    /**
     * Get's the indexed player's score.
     * 
     * @param playerIndex this function expects a index representing the player (by
     *                    order of creation)
     * @return this function returns the corresponding User's score as an int
     */
    public int getPlayerScore(int playerIndex) {
        return this.userManager.getUser(this.users.get(playerIndex)).getUserScore();
    }

    // Main input processing function
    /**
     * Will use the provided input to determine what function to be done, and will
     * return a boolean
     * of whether or not the move was turn ending
     * 
     * @param input       this function expects a single letter input out of "wasd",
     *                    "f", or "r"
     * @param playerIndex this function expects a index representing the player (by
     *                    order of creation)
     * @return this function returns boolean signaling the termination of a player's
     *         turn
     * @throws Exception in the case of no valid input
     */
    public boolean processInput(String input, int playerIndex) throws Exception {
        // Process player input or simulate game actions
        // w s a d to control the selector in board, f to confirm, r to swap
        Board currentBoard = boardList.get(playerIndex);
        switch (input) {
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
            default:
                throw new Exception();
        }
    }

    // Main updating sequence
    /**
     * Will update the given player's board by dropping tiles to appropriate
     * locations, and filling the empty
     * tiles with new ones.
     * 
     * @param playerIndex this function expects a index representing the player (by
     *                    order of creation)
     */
    public void updateGameState(int playerIndex) {
        Board currentBoard = boardList.get(playerIndex);
        int addscore = rule.runAllMatch(currentBoard);

        this.workingGUI.printToScreen("After Dropping Tiles");
        renderBoard(playerIndex);
        currentBoard.dropTiles();
        this.workingGUI.getInput("Continue");

        this.workingGUI.printToScreen("After Spawning New Ties");
        renderBoard(playerIndex);
        this.spawner.fill(currentBoard);
        this.workingGUI.getInput("Continue");

        userManager.getUser(users.get(playerIndex)).addScore(addscore);
    }
}
