package objects.entities;

import components.input.KeySet;
import components.input.PlayerKeyboard;
import main.Driver;
import main.geometry.Vector;

/**
 * Player
 */
public class Player extends JellyGuy {

	private final PlayerKeyboard keyboard;

    public Player(Vector pos, KeySet input) {
		super(pos);
		keyboard = new PlayerKeyboard(input);
        addComponent(keyboard);
		Driver.getWindow().getCanvas().addKeyListener(keyboard);
    }

    @Override
    public void fixedUpdate() {
        if (keyboard.isMoveUp()) {
			pos.setY(pos.getY() - getSpeed());
		} else if (keyboard.isMoveDown()) {
			pos.setY(pos.getY() + getSpeed());
		}
		if (keyboard.isMoveLeft()) {
			pos.setX(pos.getX() - getSpeed());
		} else if (keyboard.isMoveRight()) {
			pos.setX(pos.getX() + getSpeed());
		}

		//Keep it on screen
		
		if (pos.getX() > Driver.getWidth()) {
			pos.setX(-getRadius() * 2);
		} else if (pos.getX() < -getRadius() * 2) {
			pos.setX(Driver.getWidth());
		}
		
		if (pos.getY() > Driver.getHeight()) {
			pos.setY(-getRadius() * 2);
		} else if (pos.getY() < -getRadius() * 2) {
			pos.setY(Driver.getHeight());
		}
    }

}