package TMGE;


public class Test {

	// private void runTestingGame() {
	// 	UserManager manager = UserManager.getInstance();
	// 	Game game = new Game();

	// 	manager.addUser("Test Player 1");
	// 	manager.addUser("Test Player 2");

	// 	//game


	private static void randomSpawnTest(){
			Board someBoard = new Board(4,4);
			System.out.println(someBoard);

			Spawner someSpawner = new Spawner();
			someBoard.utilize(someSpawner);

			for (int i = 0; i < 16; i++){
				someBoard.spawn();
			}
			System.out.println(someBoard);
	}
	public static void main(String[] args) {
		randomSpawnTest();
	}

}