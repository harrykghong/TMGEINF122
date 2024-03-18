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
import java.util.ArrayList;

public class Spawner {
	//public Board board;
	ArrayList<Tile> tileList;
	String pattern;

	public Spawner() {
		this.pattern = "default: 0-8";
		this.tileList = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
            tileList.add(new Tile(i));
        }
	}

	public Spawner(ArrayList<Tile> predeterminedTileList) {
		this.pattern = "predetermined";
		this.tileList = predeterminedTileList;
		//System.out.println("'seconds'");
	}

	public void spawn(Board board){
		Random random = new Random();
        int randomNumber = random.nextInt(this.tileList.size());
		Tile someTile = this.tileList.get(randomNumber);

		int randomIndex = getRandomElement(board.openSpaces);

		System.out.println("tile:"+someTile+" randomIndex:"+randomIndex);
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
