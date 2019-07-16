package objects.stage;

import main.Driver;
import main.geometry.Vector;
import objects.Grid;
import objects.entities.Apple;
import objects.entities.Coin;

public class GameTest extends Stage {
	
	private static int numballs = 10;

	public GameTest() {
		super("Interactive Test Stage");
		addObject(new Apple(new Vector(100,100)));
		addObject(new Grid(25));
		
		for(int i = 0; i < numballs; i++) {
			addObject(new Coin(new Vector(Math.random()*Driver.getWidth(),Math.random()*Driver.getHeight())));
		}
	}
}
