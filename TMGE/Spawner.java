package TMGE;
// Spawner Class

/*
Attached to board, it randomly picks a random empty space to spawn a tile
*/

// Imports
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;

public class Spawner {
	ArrayList<Tile> tileList;
	String pattern;

	/**
	 * Defaulted Constructor. Will construct with a tile list of 1-8.
	 */
	public Spawner() {
		this.pattern = "default: 0-8";
		this.tileList = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			tileList.add(new Tile(i));
		}
	}

	/**
	 * Will construct a spawner with the given tile list. Does not check for shallow copies or duplicate values.
	 * @param predeterminedTileList
	 */
	public Spawner(ArrayList<Tile> predeterminedTileList) {
		this.pattern = "predetermined";
		this.tileList = predeterminedTileList;
	}

	/**
	 * Will spawn a new tile at a random open space on the board.
	 * @param board
	 */
	public void spawn(Board board) {
		Random random = new Random();
		int randomNumber = random.nextInt(this.tileList.size());
		Tile someTile = new Tile(this.tileList.get(randomNumber));

		int randomIndex = getRandomElement(board.openSpaces);
		board.openSpaces.remove(randomIndex);
		board.addTile(someTile, randomIndex);
	}

	/**
	 * Will spawn a new random tile from the tile list into every open space
	 * on a given board.
	 * @param board
	 */
	public void fill(Board board) {
		while (board.openSpaces.size() > 0) {
			spawn(board);
		}
	}

	/**
	 * Function to get a random element of a set. Used in determining random spawning space.
	 * @param set
	 * @return E
	 */
	private static <E> E getRandomElement(Set<? extends E> set) {
		if (set.size() == 0)
			return null;
		Random random = new Random();

		int randomNumber = random.nextInt(set.size());

		Iterator<? extends E> iterator = set.iterator();

		int currentIndex = 0;
		E randomElement = null;

		while (iterator.hasNext()) {
			randomElement = iterator.next();
			if (currentIndex == randomNumber)
				return randomElement;
			currentIndex++;
		}

		return randomElement;
	}

}
