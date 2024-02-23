/*
Attached to board, it randomly picks a random empty space to spawn a tile
*/
import java.util.concurrent.ThreadLocalRandom;

import java.util.Random;





public class Spawner {
	public Board board;
	private String pattern;

	public Spawner() {
		this.pattern = "random";
	}

	public void spawn() {
		Tile someTile = new Tile(ThreadLocalRandom.current().nextInt(0, 9));

		switch (this.pattern) {
		case "random":
			System.out.println("Spawner:spawn: fixme");

			System.out.println("Listing OpenSpaces");
			System.out.println(this.board.openSpaces);

			Random random = new Random();
			Integer[] arrayNumbers = this.board.openSpaces.toArray(new Integer[this.board.size]); 
        	int randomIndex=-1;
			try {
				randomIndex= arrayNumbers[random.nextInt(this.board.size)];
			}
			catch(Exception e) {
				break;
			}
			int row = randomIndex / this.board.col;
			int col = randomIndex % this.board.col;

			//int col = ThreadLocalRandom.current().nextInt(0, this.board.col);
			//int row = ThreadLocalRandom.current().nextInt(0, this.board.row);

			// Tile targetSpace = this.board.getRandomOpenSpace();
			this.board.addTile(someTile, col, row);
			break;

		default:
			break;
		}
	}
    
}
