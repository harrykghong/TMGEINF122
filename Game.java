// Testing Game Class
/*
	The main gameloop
*/

// Imports

public class Game {
	Board[] boardArray;
	private boolean isRunning;
	private UserManager userManager;
	
	public Game() {
		this.boardArray = new Board[2];
		Board board1 = new Board(4, 4); //Fixme
		Board board2 = new Board(4, 4); //Fixeme
		
		this.boardArray[0] = board1;
		this.boardArray[1] = board2;

		this.userManager = UserManager.getInstance();
	}
	public void run() {

		// we should process one player at a time.
        while (isRunning) {
			boolean player1turn = false;
			boolean player2turn = false;
			while(!player1turn){
				// keep listening player1's move and player2's move
				if(processInput()){
					player1turn = true;
				}
			}
			while(!player2turn){

				if(processInput()){
					player2turn = 
eru
					
				};
			}
             // Process input from the player or automated system
            updateGameState(); // Update the game state based on rules
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

    private void processInput() {
        // Process player input or simulate game actions
    }

    private void updateGameState() {
        // Update your game board and game state here
        boardArray[0].update();
		boardArray[1].update();
    }

    private void render() {
		System.out.println("Player 1 Board:");
		System.out.println(boardArray[0].toString());
		System.out.println("Player 2 Board:");
		System.out.println(boardArray[1].toString());
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
