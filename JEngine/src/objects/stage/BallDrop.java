package objects.stage;

import java.awt.Color;

import main.geometry.Vector;
import objects.entities.Ball;

public class BallDrop extends Stage {

	public BallDrop() {
		super("Ball Drop Test Stage");
		addObject(new Ball(new Vector(100,100), Color.BLACK, 50));
	}
}
