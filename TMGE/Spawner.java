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
	}

	public void spawn(Board board){
		Tile someTile = new Tile(ThreadLocalRandom.current().nextInt(1, 9));
		int randomIndex = getRandomElement(board.openSpaces);
		board.openSpaces.remove(randomIndex);
		board.addTile(someTile, randomIndex);
		System.out.println("Tile spawned:"+someTile);
	}

	public void fill(Board board){
		while (board.openSpaces.size() > 0) {
			spawn(board);
		}
	}

	private static <E>  E getRandomElement(Set<? extends E> set) 
    { 
		if (set.size()==0) return null;
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
