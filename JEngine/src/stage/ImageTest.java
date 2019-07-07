package stage;

import geometry.Vector;
import objects.Apple;
import objects.Grid;

public class ImageTest extends Stage {

	public ImageTest() {
		super("Image Test Stage");
		addObject(new Apple(new Vector(100,100)));
		addObject(new Grid(10));
	}
}
