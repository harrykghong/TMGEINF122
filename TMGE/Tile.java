package TMGE;
// Tile Class
/*
Tiles represent the objects that fill the board
Tiles have a color
*/

// Imports
import java.awt.Color;
  
public class Tile {

	enum Color {
		NULL,	//0
		RED,	//1
		GREEN,	//2
		BLUE,	//3
		YELLOW,	//4
		ORANGE,	//5
		PURPLE,	//6
		BLACK,	//7
		WHITE	//8
	}

	Color color;

    
  //public static final Color VERY_LIGHT_RED = new Color(255,102,102);
  // do we need 3  seperate values in constructor?

    public Tile (Color someColor) {
        this.color = someColor ;
    }
	public Tile(int colorID) {
        this.color = new Color(colorID); //COLOR ID DOES NOT TRANSLATE TO RGB VALUE, THIS IS SO MISLESADIGN
        // Would be good to have a list of color enumsb here
	}

	public Color getColor() {
		return this.color;
	}

    @Override
	public String toString() {
        return "Color: "+ this.color;
    }

@Override
    public boolean equals(Object obj) {
        retu
    }
}
