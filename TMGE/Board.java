package TMGE;

/*
The board of the game. It has matrix array that can hold tiles and represents the game's current state.
It spawns in tiles.
*/
// Board Class

// Imports
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

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
	private Spawner spawner;


	// Matthew's Work RN
	public Board(Board template) {
		this(template.row, template.col);
	}



	public Board(int row, int col) {
		this.tileArray = new Tile[row][col];
		this.row = row;
		this.col = col;
		this.size = row * col;
		this.selectorX = -1;
		this.selectorY = -1;
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

	public void addTile(Tile someTile, int col, int row) {
		int index = row + col * (this.row);
		this.openSpaces.remove(index);
		this.tileArray[col][row] = someTile;
	}

	public void removeTile(int col, int row) {
		int index = row + col * (this.row);
		this.openSpaces.add(index);
		this.tileArray[col][row] = new Tile(0);
	}

	public int toIndex(int col, int row) {
		return row + col * (this.row);
	}
	public Tile getTile(int col, int row) {
		return this.tileArray[col][row];
	}

	public void utilize(Spawner someSpawner) {
		this.spawner = someSpawner;
		this.spawner.board = this;
	}

	public void spawn() {
		this.spawner.spawn();
	}


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

	public void getIndex(){
		
	}

	public void update(String rule){
		switch(rule){
			case "match 3 horizontal":
				checkHorizontal(selectedTiles[0].getKey(), selectedTiles[0].getValue());
				checkHorizontal(selectedTiles[1].getKey(), selectedTiles[1].getValue());
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

		String returnString = "---------\n";
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				Tile.Color value = this.tileArray[i][j].getColor();
                // returnString+=value;
				if (value.ordinal() == 0) {
					returnString += "x,";
                }
				else {
					returnString += value.ordinal() + " ";
                }
			}
			returnString += "\n";
		}
		return returnString;

	}




//targetValue = color, recall for each color?
/*
1111
2222
3333
4444

this requires 4 calls of this function, each time for a different targetValue
*/



public boolean verticleMatchX(int[][] matrix, int targetValue) {
	int rows = matrix.length;
    int cols = matrix[0].length;



    for (int j = 0; j < cols; j++) {
        for (int i = 0; i < rows - 3; i++) {
            if (matrix[i][j] == targetValue &&
                matrix[i + 1][j] == targetValue &&
                matrix[i + 2][j] == targetValue &&
                matrix[i + 3][j] == targetValue) {
                return true;

            }
        }
    }

}

public  boolean matchX(int[][] matrix, int targetValue) {
    int rows = matrix.length;
    int cols = matrix[0].length;

	verticleMatchX();
	//Horizontal Match
    for (int i = 0; i < rows; i++) {
        if (matrix[i][0] != targetValue) break;
		for (int j = 0; j < cols; j++) {
			if (matrix[i][j] != targetValue) break;
        }
		// do not return, mark index for deletion
    }


    // Ascending Diagonal Check
    for (int i = 3; i < rows; i++) {
        for (int j = 0; j < cols - 3; j++) {
            if (matrix[i][j] == targetValue &&
                matrix[i - 1][j + 1] == targetValue &&
                matrix[i - 2][j + 2] == targetValue &&
                matrix[i - 3][j + 3] == targetValue) {
                return true;
            }
        }
    }

    // Descending Diagonal Check
    for (int i = 3; i < rows; i++) {
        for (int j = 3; j < cols; j++) {
            if (matrix[i][j] == targetValue &&
                matrix[i - 1][j - 1] == targetValue &&
                matrix[i - 2][j - 2] == targetValue &&
                matrix[i - 3][j - 3] == targetValue) {
                return true;
            }
        }
    }

    return false;
}





private void sweepVertical(){

	for (int col = 0; row < this.tileArray[0].length; col++) {
		for (int row = 0; row < this.tileArray.length; row++) {
			Tile someTiel = this.tileArray[row][col];
		}

	}
	//Return the ones marked for deltete
}


private void sweepHorizontal(){
	for (int row = 0; row < this.tileArray.length; row++) {
		sweepHorizontal(this.tileArray[row]);
	}
	//Return the ones marked for deltete
}

private void sweepHorizontal(Tile[] tiles) {
	//In an array, determine if there are 3 or more in a row and mark for delete
	//Return the ones marked for delete
	// TODO Auto-generated method stub

	Set temp = new HashSet<Integer>();
	Tile someTile = tiles[0];
	int matchCount = 0;
	for (Tile nextTile: tiles){
		temp.add(nextTile.getIndex());
		if (nextTile == someTile) matchCount++;
		else {someTile = nextTile; matchCount = 0;}

		if (matchCount >= 3)

	}


	throw new UnsupportedOperationException("Unimplemented method 'sweepHorizontal'");
}




private boolean checkHorizontal(int x, int y) {
    Object target = this.tileArray[x][y];
    int matchCount = 1; // Include the target element itself
    
	Set<Integer> temp = new HashSet<Integer>();
    // Check left
    for (int i = y - 1; i >= 0; i--) {
		if (this.tileArray[x][i].equals(target)) {
			temp.add(toIndex(x,i));
			matchCount++;
		}
	}
    
    // Check right
    for (int i = y + 1; i < this.tileArray[x].length && this.tileArray[x][i].equals(target); i++) {
        matchCount++;
    }
    
    return matchCount >= 3; // Assuming a match is 3 or more elements
}

private boolean checkVertical(int x, int y) {
    Object target = this.tileArray[x][y];
    int matchCount = 1; // Include the target element itself
    
    // Check up
    for (int i = x - 1; i >= 0 && this.tileArray[i][y].equals(target); i--) {
        matchCount++;
    }
    
    // Check down
    for (int i = x + 1; i < this.tileArray.length && this.tileArray[i][y].equals(target); i++) {
        matchCount++;
    }
    
    return matchCount >= 3; // Assuming a match is 3 or more elements
}






}

