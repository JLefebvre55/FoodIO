package collision;
import java.awt.Color;

import components.FollowComponent;
import geometry.Vector;
import objects.RenderObject;
import screen.FrameRenderer;

public abstract class Collider extends FollowComponent {

	protected boolean isSolid;
	public static boolean DRAWCOLLIDERS = true;
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
		if(DRAWCOLLIDERS) {
			drawCollider(screen);
		}
	}
	
	public void fixedUpdate() {};

	//ALL ABSTRACTS FOR COLLISIONS WITH ALL TYPES
	
	abstract public boolean isCollidingWith(CircleCollider other);
	
	//abstract public boolean isCollidingWith(BoxCollider other);
	
	abstract public boolean isCollidingWith(Vector p);
	
	abstract public void drawCollider(FrameRenderer screen);

}
