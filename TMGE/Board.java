package TMGE;

// Board Class
/*
The board of the game. It has matrix array that can hold tiles and represents the game's current state.
It spawns in tiles.
*/

// Imports
import java.util.*;

public class Board {

	public Tile[][] tileArray;
	public int row;
	public int col;
	public int size;

	// Selector indicates the highlighted tile
	public int selectorX;
	public int selectorY;
	public int saveX;
	public int saveY;

	Set<Integer> openSpaces;

	// Matthew's Work RN

	/**
	 * Board Constructor #1
	 * @param template this constructor constructs a Board object of equal size to the one given
	 */
	public Board(Board template) {
		this(template.row, template.col);
		resetAttributes();
	}

	// Secondary Constructor
	/**
	 * Board Constructor #2
	 * @param twoDArray this constructor creates and initializes a board based on the size and values inside a 2d it array
	 */
	public Board(int[][] twoDArray) {

		this.row = twoDArray.length;
		this.col = twoDArray[0].length;
		resetAttributes();

		try {
			for (int i = 0; i < this.row; i++) {
				for (int j = 0; j < this.col; j++) {
					this.addTile(new Tile(twoDArray[i][j]), i, j);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Board Constructor failure, likely input array is incorrectly sized.");
			e.printStackTrace();
		}

	}

	// Sub-constructor
	/**
	 * Board Constructor #3
	 * @param row the desired height of the board
	 * @param col the desired width of the board
	 */
	public Board(int row, int col) {
		this.row = row;
		this.col = col;
		resetAttributes();
	}

	// Board Resetting
	/**
	 * Resets the board back to a blank slate with the establisted row and col.
	 */
	public void resetAttributes() {
		this.tileArray = new Tile[row][col];
		this.size = row * col;
		this.selectorX = 0;
		this.selectorY = 0;
		this.saveX = -1;
		this.saveY = -1;
		this.openSpaces = new HashSet<Integer>();

		for (int i = 0; i < this.size; i++) {
			this.openSpaces.add(i);
		}

		for (int i = 0; i < this.tileArray.length; i++) {
			for (int j = 0; j < this.tileArray[i].length; j++) {
				this.tileArray[i][j] = new Tile(0);
			}
		}
	}

	// Tile Addition
	/**
	 * Function to add a Tile to the board given row and col ints.
	 * @param someTile the tile to be added (does not check for shallow copies)
	 * @param row 
	 * @param col
	 */
	public void addTile(Tile someTile, int row, int col) {
		int index = toIndex(row, col);
		this.openSpaces.remove(index);
		this.tileArray[row][col] = someTile;
	}

	/**
	 * Function to add a tile to the board at a specfic inddex based on established logic.
	 * @param someTile
	 * @param index
	 */
	public void addTile(Tile someTile, int index) {
		int col = index / this.row;
		int row = index % this.row;
		this.openSpaces.remove(index);
		this.tileArray[row][col] = someTile;
	}

	/**
	 * Function to remove Tiles at a specific row and col int. Will set the tile back to a null state (0).
	 * @param row
	 * @param col
	 */
	public void removeTile(int row, int col) {
		int index = row + col * (this.row);
		this.openSpaces.add(index);
		this.tileArray[row][col] = new Tile(0);
	}

	/**
	 * @param index the index of the target tile
	 */
	public void removeTile(int index) {
		int col = index / this.row;
		int row = index % this.row;
		this.openSpaces.add(index);
		this.tileArray[row][col] = new Tile(0); // 0 = empty or null
	}

	/**
	 * Function to turn row and col into an index value.
	 * @param row
	 * @param col
	 * @return this function returns the index of the coordinates
	 */
	public int toIndex(int row, int col) {
		return row + col * (this.row);
	}

	/**
	 * Function to get a tile at a specific row and col.
	 * @param row
	 * @param col
	 * @return this function returns the Tile at the coordinates
	 */
	public Tile getTile(int row, int col) {
		return this.tileArray[row][col];
	}

	/**
	 * @param index
	 * @return this function returns the Tile at the coordinates
	 */
	public Tile getTile(int index) {
		int row = index / this.row;
		int col = index % this.row;
		return this.getTile(row, col);
	}

	/**
	 * Function to perform swap at the selected tile and the saved tile.
	 * @return this function returns a boolean represnting whether the operation was sucessful
	 */
	public boolean selectorSwap() {

		if (saveX < 0 || saveY < 0)
			return false;
		Tile.Color color1 = getTile(saveX, saveY).getColor();
		Tile.Color color2 = getTile(selectorX, selectorY).getColor();
		this.getTile(saveX, saveY).setColor(color2);
		this.getTile(selectorX, selectorY).setColor(color1);
		saveX = -1;
		saveY = -1;
		return true;
	}

	/**
	 * Function to change the saved values to that of the selector.
	 * @return this function returns a boolean represnting whether the operation was sucessful
	 */
	public boolean selectorSelects() {
		this.saveX = this.selectorX;
		this.saveY = this.selectorY;
		if (this.saveX < 0 || this.saveY < 0)
			return false;
		return true;
	}

	/**
	 * Function to move selector up.
	 * @return this function returns a boolean represnting whether the operation was sucessful
	 */
	public boolean moveSelectorUp() {
		if (this.selectorX > 0) {
			this.selectorX--;
			return true;
		}
		return false;
	}
	
	/**
	 * Function to move selector down.
	 * @return this function returns a boolean represnting whether the operation was sucessful
	 */
	public boolean moveSelectorDown() {
		if (this.selectorX + 1 < row) {
			this.selectorX++;
			return true;
		}
		return false;
	}
	
	/**
	 * Function to move selector left.
	 * @return this function returns a boolean represnting whether the operation was sucessful
	 */
	public boolean moveSelectorLeft() {
		if (this.selectorY > 0) {
			this.selectorY--;
			return true;
		}
		return false;
	}
	
	/**
	 * Function to move selector right.
	 * @return this function returns a boolean represnting whether the operation was sucessful
	 */
	public boolean moveSelectorRight() {
		if (this.selectorY + 1 < col) {
			this.selectorY++;
			return true;
		}

		return false;
	}

	/**
	 * Function to simulate gravity within the board, dropping tiles to their lowest possible space.
	 */
	public void dropTiles() {
		for (int currentCol = 0; currentCol < this.col; currentCol++) {
			Queue<Tile> queue = new LinkedList<>();
			for (int currentRow = this.row - 1; currentRow >= 0; currentRow--) {
				Tile currentTile = this.getTile(currentRow, currentCol);
				if (currentTile.color != Tile.Color.NULL) {
					queue.add(currentTile);
				}
			}
			for (int currentRow = this.row - 1; currentRow >= 0; currentRow--) {
				if (!queue.isEmpty()) {
					this.addTile(queue.poll(), currentRow, currentCol);
				} else {
					this.removeTile(currentRow, currentCol);
				}
			}
		}
	}

	/**
	 * toString of Board. Returns a string object that represents the board and its tiles.
	 * @return this function returns a string representation of the board state
	 */
	@Override
	public String toString() {
		Tile.Color value;
		String returnString = "---------\n";
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				value = this.tileArray[i][j].getColor();
				String addString = "";
				if (value.ordinal() == 0) {
					addString += "-x-";
				} else {
					addString += value.ordinal() + " ";
				}

				if (isSaveTile(i, j)) {
					addString = "(" + addString + ")";
				} else {
					addString = " " + addString + " ";
				}

				if (isSelectorTile(i, j)) {
					returnString += "[" + addString + "]";
				} else {
					returnString += " " + addString + " ";
				}
			}
			returnString += "\n";
		}
		return returnString;

	}

	/**
	 * Checks to see if the tile at x and y is where the selector is currently.
	 * @param x
	 * @param y
	 * @return boolean isSelectorTile
	 */
	private boolean isSelectorTile(int x, int y) {
		return x == this.selectorX && y == this.selectorY;
	}

	/**
	 * Checks to see if the tile at x and y is where the saved tile is currently.
	 * @param x
	 * @param y
	 * @return boolean isSavedTile
	 */
	private boolean isSaveTile(int x, int y) {
		return x == this.saveX && y == this.saveY;
	}

}
