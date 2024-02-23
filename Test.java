public class Test {

	// private void runTestingGame() {
	// 	UserManager manager = UserManager.getInstance();
	// 	Game game = new Game();

	// 	manager.addUser("Test Player 1");
	// 	manager.addUser("Test Player 2");

	// 	//game
	// } // i might need to commetn this out for tesitng

	public static void main(String[] args) {


		Board someBoard = new Board(4,4);
		System.out.println(someBoard);

		Spawner someSpawner = new Spawner();
		someBoard.utilize(someSpawner);

		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();

		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();
		System.out.println(someBoard);

		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();

		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();
		someBoard.spawn();
		System.out.println(someBoard);

		//Game someGame = new Game();
	}

}