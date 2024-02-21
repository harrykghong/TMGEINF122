public class Game {
	Board[] boardArray;

	public Game() {
		this.boardArray = new Board[2];
		Board board1 = new Board(4, 4); //Fixme
		Board board2 = new Board(4, 4); //Fixeme
		this.boardArray[0] = board1;
		this.boardArray[1] = board2;
	}

	@Override
	public String toString() {
		String returnString = "";
		for (Board someBoard : this.boardArray) {
			returnString += someBoard;
		}
		return returnString;

	}
}
