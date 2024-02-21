import java.util.concurrent.ThreadLocalRandom;

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
			int col = ThreadLocalRandom.current().nextInt(0, this.board.col);
			int row = ThreadLocalRandom.current().nextInt(0, this.board.row);

			// Tile targetSpace = this.board.getRandomOpenSpace();
			this.board.addTile(someTile, col, row);
			break;

		default:
			break;
		}
	}
    
}
