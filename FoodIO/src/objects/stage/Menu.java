package objects.stage;

import main.Driver;

import objects.StringObject;
import components.input.KeySet;
import components.input.PlayerKeyboard;

public class Menu extends Stage {
	
	private static int numballs = 15;

	public Menu() {
        super("Main Menu");
        StringObject s = new StringObject(Driver.getCenter(), "Press Space to Start.");
        s.addComponent(new PlayerKeyboard(KeySet.PLAYERONE));
        addObject(s);
	}

	@Override
	public void refreshStage() {
        
	}
}
