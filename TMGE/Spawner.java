package TMGE;
// Spawner Class
/*
Attached to board, it randomly picks a random empty space to spawn a tile
*/

// Imports
import java.util.concurrent.ThreadLocalRandom;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Spawner {
	//public Board board;
	private String pattern;

	public Spawner() {
		this.pattern = "random";
		System.out.println("Spawner only spawns in random configurations atm");
	}

	public void spawn(Board board){
		Tile someTile = new Tile(ThreadLocalRandom.current().nextInt(1, 9));
		int randomIndex = getRandomElement(board.openSpaces);
		board.openSpaces.remove(randomIndex);
		//int col = randomIndex / board.row;
		//int row = randomIndex % board.row;
		board.addTile(someTile, randomIndex);
	}

	public void fill(Board board){
		while (board.openSpaces.size() > 0) {
			spawn(board);
		}
	}

	// public void spawn() {
	// 	//Random value tile 
	// 	Tile someTile = new Tile(ThreadLocalRandom.current().nextInt(1, 9));
	// 	//Tile someTile = new Tile(1);
		
	// 	switch (this.pattern) {
	// 	case "random":
	// 		try {
	// 			int randomIndex = getRandomElement(this.board.openSpaces);
	// 			this.board.openSpaces.remove(randomIndex);
	// 			int col = randomIndex / this.board.row;
	// 			int row = randomIndex % this.board.row;
	// 			this.board.addTile(someTile, col, row);
	// 		}
	// 		catch(Exception e) {
	// 			System.out.println("error spawning; likely no possible spots");
	// 			break;
	// 		}

	// 		break;

	// 	default:
	// 		break;
	// 	}
	// }

	private static <E>  E getRandomElement(Set<? extends E> set) 
    { 
		if (set.size()==0) return null;
        Random random = new Random(); 
  
        // Generate a random number using nextInt 
        // method of the Random class. 
        int randomNumber = random.nextInt(set.size()); 
  
        Iterator<? extends E> iterator = set.iterator(); 
  
        int currentIndex = 0; 
        E randomElement = null; 
  
        // iterate the HashSet 
        while (iterator.hasNext()) { 
  
            randomElement = iterator.next(); 
  
            // if current index is equal to random number 
            if (currentIndex == randomNumber) 
                return randomElement; 
  
            // increase the current index 
            currentIndex++; 
        } 
  
        return randomElement; 
    } 




    
}
