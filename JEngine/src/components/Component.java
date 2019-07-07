package components;

import main.Renderable;
import objects.RenderObject;

/**
 * 
 * @author jaydenlefebvre
 * TODO make OBJECTS have COMPONENTS
 */
public abstract class Component implements Renderable{

	private final boolean GRAPHICAL;
	
	public Component(boolean graphical) {
		this.GRAPHICAL = graphical;
	}
	
	public boolean isGraphical() {
		return GRAPHICAL;
	}
	
}
