package TMGE;
// Tile Class
/*
Tiles represent the objects that fill the board
Tiles have a color
*/

// Imports
//import java.awt.Color;
  
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

    public Tile(Color someColor) {
        this.color = someColor;
    }
	public Tile(int colorID) {
		try {
			this.color = Color.values()[colorID];
		} catch (Exception e) {
			System.out.println("Tile Constructor failure");
			e.printStackTrace();
		}
	}

	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

    @Override
	public String toString() {
        return "Color: "+ this.color;
    }

@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
 
        if (!(obj instanceof Tile)) {
            return false;
        }
         
		return this.color == ((Tile) obj).color;
    }
	
}
