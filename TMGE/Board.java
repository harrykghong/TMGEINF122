package TMGE;

/*
The board of the game. It has matrix array that can hold tiles and represents the game's current state.
It spawns in tiles.
*/
// Board Class

// Imports
import java.util.*;


//import java.util.HashMap;
//import java.util.Map;
//import java.awt.Color;


public class Board {

	Tile[][] tileArray;
	int row;
	int col;
	int size;

	//Selector indicates the highlighted tile
	int selectorX;
	int selectorY;

	int saveX;
	int saveY;


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

		//May be not usable
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

	// public void fill(){
	// 	while (this.openSpaces.size() > 0) this.spawner.spawn(this);
	// }

	public void addTile(Tile someTile, int row, int col) {
		int index = getIndex(row, col);
		this.openSpaces.remove(index);
		this.tileArray[row][col] = someTile;
	}

	public void addTile(Tile someTile, int index) {
		int row = index / this.row;
    	int col = index % this.row;
		this.openSpaces.remove(index);
		this.tileArray[row][col] = someTile;
	}

	public void removeTile(int row, int col) {
		int index = row + col * (this.row);
		this.openSpaces.add(index);
		this.tileArray[row][col] = new Tile(0);
	}

	public void removeTile(int index) {
		int row = index / this.row;
    	int col = index % this.row;
		this.openSpaces.add(index);
		this.tileArray[row][col] = new Tile(0);
	}

	public int toIndex(int row, int col) {
		return row + col * (this.row);
	}

	public Tile getTile(int row, int col) {
		//System.out.println("getting tile "+row+","+col);
		return this.tileArray[row][col];
	}
	public Tile getTile(int index) {
		int row = index / this.row;
    	int col = index % this.row;
		return this.getTile(row, col);
	}

	// public void utilize(Spawner someSpawner) {
	// 	this.spawner = someSpawner;
	// }

	// public void spawn() {
	// 	//if (this.spawner == null) System.out.println("Board spawner not set");
	// 	this.spawner.spawn(this);
	// }


	public boolean selectorSwap() {
		if (saveX < 0 || saveY < 0) return false;
		
		Tile.Color savedValue = getTile(saveX, saveY).color;
		Tile.Color savedValue2 = getTile(selectorX,selectorY).color;

		addTile(new Tile(savedValue), selectorX, selectorY);
		addTile(new Tile(savedValue2), selectorX, selectorY);
		
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
		System.out.println(this.selectorX);
		if(this.selectorX < row){
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
		if(this.selectorX < col){
			this.selectorX++;
			return true;
		}
		return false;
	}


	public void update(List<String> ruleList){
		// loop all rules
		for (String rule: ruleList){
			update(rule);
		}
	}

	public int getIndex(int row, int col){
		return row + col * (this.row);
	}

	public void update(String rule){
		switch(rule){
			case "match 3 horizontal":
				//checkHorizontal(selectedTiles[0].getKey(), selectedTiles[0].getValue());
				break;
			case "match 3 colors":
				// colorMatch();
				break;
			case "match 3 vertical":
				// verticleMatchX();
			default:
				break;
		}
	}

	@Override
	public String toString() {
		Tile.Color value;
		String returnString = "---------\n";
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				value = this.tileArray[i][j].getColor();
				String addString = "";
				
				if (value.ordinal() == 0) {
					addString += "x";
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


public void sweepHorizontal(int matchX){
	Tile.Color currentColor;
	Set<Integer> returnSet = new HashSet<>();
	Tile tempTile;
	System.out.println("Running sweepHorizontal");
	for (int row = 0; row < this.row; row++) {
		Set<Integer> temp = new HashSet<>();
		currentColor = this.getTile(row, 0).getColor();
		for (int col = 0; col < this.col; col++) {
			tempTile = this.getTile(row, col);	//Get tile
			System.out.println("tempTile:"+tempTile);
			// same color
			if (tempTile.getColor() == currentColor) {
				temp.add(getIndex(row, col));
				// Temp Big enough
				if (temp.size() >= matchX && row == this.row-1) {
					returnSet.addAll(temp);
				}
			}
			// not same color
			else
			{
				// Temp big enough
				if (temp.size() >= matchX) {
					returnSet.addAll(temp);
				}
				temp = new HashSet<>();
				temp.add(getIndex(row, col));
				currentColor = tempTile.getColor();
			}
		}
	}
	System.out.println("remove these indexes:" + returnSet);
}



public void sweepVertical(int matchX){
	Tile.Color currentColor;
	Set<Integer> returnSet = new HashSet<>();
	Tile tempTile;
	System.out.println("Running sweepVertical");
	for (int col = 0; col < this.col; col++) {
		
		Set<Integer> temp = new HashSet<>();
		currentColor = this.getTile(0, col).getColor();
		
		for (int row = 0; row < this.row; row++) {
			tempTile = this.getTile(row, col);	//Get tile
			// same color
			if (tempTile.getColor() == currentColor) {
				temp.add(getIndex(row, col));
				// Temp Big enough
				if (temp.size() >= matchX && row == this.row-1) {
					returnSet.addAll(temp);
				}
			}
			// not same color
			else
			{
				// Temp big enough
				if (temp.size() >= matchX) {
					returnSet.addAll(temp);
				}

				temp = new HashSet<>();
				temp.add(getIndex(row, col));
				currentColor = tempTile.getColor();
			}
		}
	}
	System.out.println("remove these indexes:" + returnSet);
	}

}

