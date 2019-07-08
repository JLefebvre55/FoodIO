package stage;

import java.awt.Color;

import geometry.Vector;
import main.Driver;
import objects.Apple;
import objects.Coin;
import objects.Grid;

public class GameTest extends Stage {
	
	private static int numballs = 10;

	public GameTest() {
		super("Interactive Test Stage");
		addObject(new Apple(new Vector(100,100), 50));
		addObject(new Grid(25));
		
		for(int i = 0; i < numballs; i++) {
			addObject(new Coin(new Vector(Math.random()*Driver.getWidth(),Math.random()*Driver.getHeight())));
		}
	}
}
