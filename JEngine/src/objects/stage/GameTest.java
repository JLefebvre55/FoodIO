package objects.stage;

import java.awt.Color;

import components.input.KeySet;
import main.geometry.Vector;
import objects.Grid;
import objects.entities.Coin;
import objects.entities.Player;

public class GameTest extends Stage {
	
	private static int numballs = 15;

	public GameTest() {
		super("Interactive Test Stage");
		addObject(new Player(new Vector(100,100), KeySet.PLAYERONE, "Player 1"));
		addObject(new Player(new Vector(400,400), KeySet.PLAYERTWO, "Player 2"));
		addObject(new Grid(50, Color.GRAY));
		
		for(int i = 0; i < numballs; i++) {
			addObject(new Coin(randomVectorInBounds()));
		}
	}

	@Override
	public void refreshStage() {
		if(getObjectsByType(Coin.class).size() < numballs){
			addObject(new Coin(randomVectorInBounds()));
		}
	}
}
