/*
The board of the game. It has matrix array that can hold tiles and represents the game's current state.
It spawns in tiles.
*/
// Board Class

// Imports
import java.util.*;
//import java.util.HashMap;
//import java.util.Map;
import java.awt.Color;

class Board {

	Tile[][] tileArray;
	int row;
	int col;
	int size;
	// ArrayList<Integer> openSpaces;
	//Map<Integer, Integer> openSpaces;
	Set<Integer> openSpaces; //= new HashSet<>();

	private Spawner spawner;

	public Board(int row, int col) {

		this.tileArray = new Tile[row][col];
		this.row = row;
		this.col = col;
		this.size = row * col;

		//this.openSpaces = new HashMap<Integer, Integer>();
		this.openSpaces = new HashSet<Integer>();
		
		for (int i = 0; i < this.size; i++) {
			this.openSpaces.add(i);
			//this.openSpaces.put(i, 0);
			//int row = (int) (i / this.row);
			//int column = i % this.row;
			//System.out.println(row+column);
		}
		System.out.println(this.openSpaces);

		for (int i = 0; i < this.tileArray.length; i++) {
			for (int j = 0; j < this.tileArray[i].length; j++) {

				// Hardcoded.
				//this.addTile(new Tile(0), i, j);
				this.tileArray[i][j] = new Tile(0);
			}
		}

	}
    public Tile getRandomOpenSpace() {
		System.out.println("getRandmOpenSpace not implemented");
        //int col = ThreadLocalRandom.current().nextInt(0, this.openSpaces.size());
		return null;
	}

	public void addTile(Tile someTile, int col, int row) {

		int index = row + col * (this.row);
		this.openSpaces.remove(index);
		this.tileArray[col][row] = someTile;

        // System.out.println("Board:addTile");
        
        // for (Tile[] tileArray: this.tileArray)
        //     for (Tile aTile: tileArray)
        //         System.out.println(aTile);
	}

	public void utilize(Spawner someSpawner) {
		this.spawner = someSpawner;
		this.spawner.board = this;
	}

	public void spawn() {
		this.spawner.spawn();
	}

	@Override
	public String toString() {

		String returnString = "---------\n";
		for (int i = 0; i < this.tileArray.length; i++) {
			for (int j = 0; j < this.tileArray[i].length; j++) {
				Color value = this.tileArray[i][j].getColor();
                // returnString+=value;
				if (value.getBlue() == 0) {
					returnString += "x,";
                }
				else {
					returnString += value.getBlue() + " ";
                }
			}
			returnString += "\n";
		}
		return returnString;

	}
}

