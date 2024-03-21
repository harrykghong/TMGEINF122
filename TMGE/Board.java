package TMGE;

/*
The board of the game. It has matrix array that can hold tiles and represents the game's current state.
It spawns in tiles.
*/
// Board Class

// Imports
import java.util.*;



public class Board {

	public Tile[][] tileArray;
	public int row;
	public int col;
	public int size;

	//Selector indicates the highlighted tile
	public int selectorX;
	public int selectorY;

	public int saveX;
	public int saveY;


	Set<Integer> openSpaces;
	Set<Integer> toBeDeleted;
	//private Spawner spawner;

	public void resetAttributes(){
		this.tileArray = new Tile[row][col];

		this.size = row * col;
		this.selectorX = 0;
		this.selectorY = 0;
		this.saveX = -1;
		this.saveY = -1;
		this.openSpaces = new HashSet<Integer>();
		this.toBeDeleted= new HashSet<Integer>();

		for (int i = 0; i < this.size; i++) {
			this.openSpaces.add(i);
		}

		for (int i = 0; i < this.tileArray.length; i++) {
			for (int j = 0; j < this.tileArray[i].length; j++) {
				this.tileArray[i][j] = new Tile(0);
			}
		}
	}
	// Matthew's Work RN
	public Board(Board template) {
		this(template.row, template.col);
		resetAttributes();
	}

	public Board(int[][] twoDArray) {

		this.row = twoDArray.length;
		this.col = twoDArray[0].length;
		resetAttributes();

		//System.out.println("Board constructor\n" + this.row +" by "+ this.col);

		try {
			for (int i = 0; i < this.row; i++){
				for (int j = 0; j < this.col; j++){
					this.addTile(new Tile(twoDArray[i][j]), i, j);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Board Constructor failure, likely input array is incorrectly sized.");
			e.printStackTrace();
		}

	}

	public Board(int row, int col) {
		this.row = row;
		this.col = col;
		resetAttributes();
	}


	public void addTile(Tile someTile, int row, int col) {
		int index = getIndex(row, col);
		this.openSpaces.remove(index);
		this.tileArray[row][col] = someTile;
	}

	public void addTile(Tile someTile, int index) {
		int col = index / this.row;
    	int row = index % this.row;
		this.openSpaces.remove(index);
		this.tileArray[row][col] = someTile;
	}

	public void removeTile(int row, int col) {
		int index = row + col * (this.row);
		this.openSpaces.add(index);
		this.tileArray[row][col] = new Tile(0);
	}

	public void removeTile(int index) {
		int col = index / this.row;
    	int row = index % this.row;
		this.openSpaces.add(index);
		this.tileArray[row][col] = new Tile(0);	//0 = empty or null
	}

	public int toIndex(int row, int col) {
		return row + col * (this.row);
	}

	public Tile getTile(int row, int col) {
		return this.tileArray[row][col];
	}
	public Tile getTile(int index) {
		int row = index / this.row;
    	int col = index % this.row;
		return this.getTile(row, col);
	}


	public boolean selectorSwap() {


		if (saveX < 0 || saveY < 0) return false;
		
		Tile.Color color1 = getTile(saveX, saveY).getColor();
		Tile.Color color2 = getTile(selectorX,selectorY).getColor();

		this.getTile(saveX,saveY).setColor(color2);
		this.getTile(selectorX,selectorY).setColor(color1);
		
		saveX = -1;
		saveY = -1;
		return true;
	}

	public boolean selectorSelects(){

		this.saveX = this.selectorX;
		this.saveY = this.selectorY;
		if (this.saveX < 0 || this.saveY < 0) return false;
		return true;
	}

	public boolean moveSelectorUp(){
		if(this.selectorX > 0){
			this.selectorX--;
			return true;
		}
		return false;
	}
	public boolean moveSelectorDown(){
		if(this.selectorX+1 < row){
			this.selectorX++;
			return true;
		}
		return false;
	}
	public boolean moveSelectorLeft(){
		if(this.selectorY > 0){
			this.selectorY--;
			return true;
		}
		return false;
	}
	public boolean moveSelectorRight(){
		if(this.selectorY+1 < col){
			this.selectorY++;
			return true;
		}

		return false;
	}

	public int getIndex(int row, int col){
		return row + col * (this.row);
	}

	@Override
	public String toString() {
		Tile.Color value;
		String returnString = "---------\n";
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				value = this.tileArray[i][j].getColor();
				String addString = "";
				// System.out.println(value);
				if (value.ordinal() == 0) {
					addString += "-x-";
                }
				else {
					addString += value.ordinal() + " ";
                }
			
				if (isSaveTile(i, j)) {
					addString = "(" + addString + ")";
				}
				else {
					addString = " " + addString + " ";
				}

				if (isSelectorTile(i, j)) {
					returnString += "[" + addString + "]";
				}
				else {
					returnString += " " + addString + " ";
				}
			}
			returnString += "\n";
		}
		return returnString;

	}

	private boolean isSelectorTile(int x, int y) {
		return x == this.selectorX && y == this.selectorY;
	}

	private boolean isSaveTile(int x, int y) {
		return x == this.saveX && y == this.saveY;
	}

	public void dropTiles() {
		for (int currentCol = 0; currentCol < this.col; currentCol++) {
			Queue<Tile> queue = new LinkedList<>();
			for (int currentRow = this.row - 1; currentRow >= 0; currentRow--) {
				// store tile that is not empty in this array1
				Tile currentTile = this.getTile(currentRow, currentCol);
				if (currentTile.color != Tile.Color.NULL) {
					queue.add(currentTile);
				}
			}
			for (int currentRow = this.row - 1; currentRow >= 0; currentRow--) {
				// from bottom to top fill the tile we stored in array1 with another loop
				// and the rest would be empty
				if (!queue.isEmpty()) {
					//Shifts tiles downwards
					this.addTile(queue.poll(), currentRow, currentCol);
				}
				else {
					this.removeTile(currentRow,currentCol);
				}
			}
		}
	}

}

