package components.collision;
import java.awt.Color;

import components.FollowComponent;
import main.Driver;
import main.geometry.Vector;
import main.rendering.FrameRenderer;
import objects.RenderObject;

public abstract class Collider extends FollowComponent {

	protected boolean isSolid;
	public static final Color DRAWCOLOR = Color.GREEN;
	
	public Collider(RenderObject parent) {
		this(parent, new Vector(0,0));
		// TODO Auto-generated constructor stub
	}
	
	public Collider(RenderObject parent, Vector delta) {
		super(parent, delta, true);
		// TODO Auto-generated constructor stub
	}
	
	public void update(FrameRenderer screen) {
		if(Driver.DEBUG) {
			drawCollider(screen);
		}
	}

	//ALL ABSTRACTS FOR COLLISIONS WITH ALL TYPES
	
	abstract public boolean isCollidingWith(CircleCollider other);
	
	abstract public boolean isCollidingWith(BoxCollider other);
	
	abstract public boolean isCollidingWith(Vector p);
	
	abstract public void drawCollider(FrameRenderer screen);

	public String toString() {
		return "Collider "+super.toString();
	}

	public boolean isCollidingWith(Collider cb) {
		// TODO Auto-generated method stub
		if(cb instanceof CircleCollider) {
			return isCollidingWith((CircleCollider)cb);
		} else if(cb instanceof BoxCollider) {
			return isCollidingWith((BoxCollider)cb);
		}
		return false;
	}
}
