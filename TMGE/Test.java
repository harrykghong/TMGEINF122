package TMGE;

public class Test {

	// private void runTestingGame() {
	// 	UserManager manager = UserManager.getInstance();
	// 	Game game = new Game();

	// 	manager.addUser("Test Player 1");
	// 	manager.addUser("Test Player 2");

	// 	//game


	private static void boardSweepTest(){
		//int[][] arr = { {{1, 2}, {3, 4}} };
		int[][] arr = {{1,1,3}, 
					   {2,2,5},
					   {2,4,2},
					   {3,4,4}};

		Board testBoard = new Board(arr);

		//Spawner someSpawner = new Spawner();
		//testBoard.utilize(someSpawner);
		System.out.println(testBoard);
		testBoard.sweepHorizontal(2);

	}

	private static void tileTest(){
		Tile tile1 = new Tile(1);
		Tile tile2 = new Tile(1);
		Tile tile3 = new Tile(2);
		System.out.println("is tile1 equal to tile2? " + tile1.equals(tile2));
		System.out.println("Tile 1 " + tile1);
		System.out.println("Tile 2 " + tile2);
		System.out.println("Tile 3 " + tile3);
	}

	private static void testBoard(){
		Board testBoard = new Board(5,5);
		System.out.println(testBoard.toString());
		System.out.println("testing addTile(new Tile(1), 0, 0): ");
		testBoard.addTile(new Tile(1), 0, 0);
		System.out.println(testBoard);
	}

	private static void testScore(){
		System.out.println("Testing Score.java");
		Score testScore = new Score();
		System.out.println("Test Constructor: Score:" + testScore.getScore());
		//Score should be able to add points
		testScore.addPoints(10);
		System.out.println("addPoints(10): Expected 10 | " + testScore.getScore());
		//Score should be able to remove points
		testScore.removePoints(20);
		System.out.println("RemovePoints(20): Expected -10 | " + testScore.getScore());
		System.out.println("Score Tests complete");
	}

	private static void randomSpawnTest(){
			Board someBoard = new Board(4,4);
			System.out.println(someBoard);

			//Spawner someSpawner = new Spawner();
			//someBoard.utilize(someSpawner);

			// for (int i = 0; i < 16; i++){
			// 	someBoard.spawn();
			// }
			System.out.println(someBoard);
	}

	public static void main(String[] args) {
		System.out.println("Running tests");
		boardSweepTest();
		//tileTest();
		//testBoard();
		//testScore();
		System.out.println("End tests");
	}

}