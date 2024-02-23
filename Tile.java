// Tile Class
/*
Tiles represent the objects that fill the board
Tiles have a color
*/

// Imports
import java.awt.Color;
  
public class Tile {
    
	Color color;

    
  //public static final Color VERY_LIGHT_RED = new Color(255,102,102);
  // do we need 3  seperate values in constructor?

	public Tile(int colorID) {
        this.color = new Color(colorID);
        // switch (colorID) {
        //     default:
        //     this.color = Color.RED;
    // }

        //this.color = new Color(colorName);
	}

	public Color getColor() {
		return this.color;
	}

    @Override
	public String toString() {
        return "Color: "+ this.color;
    }
}
