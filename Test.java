public class Test {

	public static void main(String[] args) {

		Board someBoard = new Board(2, 2);
		System.out.println(someBoard);

		Spawner someSpawner = new Spawner();
		someBoard.utilize(someSpawner);

		someBoard.spawn();
		System.out.println(someBoard);
		someBoard.spawn();
		System.out.println(someBoard);
		someBoard.spawn();
		System.out.println(someBoard);
		someBoard.spawn();
		System.out.println(someBoard);

		//Game someGame = new Game();
	}

}