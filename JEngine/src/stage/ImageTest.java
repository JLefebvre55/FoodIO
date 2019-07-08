package stage;

import java.awt.Color;

import geometry.Vector;
import objects.Apple;
import objects.Ball;
import objects.Grid;

public class ImageTest extends Stage {

	public ImageTest() {
		super("Image Test Stage");
		addObject(new Apple(new Vector(100,100)));
		addObject(new Grid(10));
		addObject(new Ball(new Vector(200,100), Color.BLACK, 30));
	}
}
