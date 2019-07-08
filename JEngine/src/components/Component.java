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
	protected RenderObject parent;
	
	public Component(RenderObject parent, boolean graphical) {
		this.GRAPHICAL = graphical;
		this.parent = parent;
	}
	
	public boolean isGraphical() {
		return GRAPHICAL;
	}
	
	public String toString() {
		return "Component";
	}
	
	public RenderObject getParent() {
		return parent;
	}
	
}
