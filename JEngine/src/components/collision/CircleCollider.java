package components.collision;

import main.geometry.Vector;
import main.rendering.FrameRenderer;
import objects.RenderObject;

/**
 * TODO replace radius with Circle bounds object
 * @author jaydenlefebvre
 *
 */
public class CircleCollider extends Collider {

	private double radius;

	public CircleCollider(RenderObject following, double radius) {
		this(following, new Vector(0,0), radius);
	}
	
	public CircleCollider(RenderObject following, Vector delta, double radius) {
		super(following, delta);
		this.radius = radius;
	}
	
	public Vector getCenter() {
		return pos.add(new Vector(radius, radius));
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public boolean isCollidingWith(Vector p) {
		// TODO Auto-generated method stub
		return p.getDistance(getCenter()) <= radius;
	}
	
	@Override
	public boolean isCollidingWith(CircleCollider other) {
		// TODO Auto-generated method stub
		return getCenter().getDistance(other.getCenter()) <= radius + other.getRadius();
	}

	public void drawCollider(FrameRenderer screen) {
		screen.getGraphics().setColor(Collider.DRAWCOLOR);
		screen.getGraphics().drawArc((int)pos.getX(), (int)pos.getY(), (int)(2*radius), (int)(2*radius), 0, 360);
	}
	
	public String toString(){
		return "Circle "+super.toString();
	}

	@Override
	public boolean isCollidingWith(BoxCollider other) {
		// TODO Auto-generated method stub
		return other.isCollidingWith(this);
	}
}
