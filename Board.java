// Board Class

// Imports
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

class Board {

	Tile[][] tileArray;
	int row;
	int col;
	int size;
	// ArrayList<Integer> openSpaces;
	Map<Integer, Integer> openSpaces;

	private Spawner spawner;

	public Board(int row, int col) {

		this.tileArray = new Tile[row][col];
		this.row = row;
		this.col = col;
		this.size = row * col;

		this.openSpaces = new HashMap<Integer, Integer>();
		for (int i = 0; i < this.size; i++) {
			this.openSpaces.put(i, 0);
			//int row = (int) (i / this.row);
			//int column = i % this.row;
			//System.out.println(row+column);
		}

		for (int i = 0; i < this.tileArray.length; i++) {
			for (int j = 0; j < this.tileArray[i].length; j++) {

				//System.out.println(this.row + "," + this.col + " " + i + "," + j);
				// this.tileArray[i][j] = new Tile(0);
				this.addTile(new Tile(10 * i + j), i, j);

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
		this.openSpaces.put(index, 0);
		this.tileArray[col][row] = someTile;

        System.out.println("Board:addTile");
        
        for (Tile[] tileArray: this.tileArray)
            for (Tile aTile: tileArray)
                System.out.println(aTile);
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

