package components.collision;


import main.geometry.Vector;
import main.rendering.FrameRenderer;
import objects.RenderObject;

public class BoxCollider extends Collider {

	private double width, height;

	public BoxCollider(RenderObject parent, double width, double height) {
		this(parent, new Vector(), width, height);
	}

	public BoxCollider(RenderObject parent, Vector delta, double width, double height) {
		super(parent, delta);
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean isCollidingWith(CircleCollider other) {
		// TODO Auto-generated method stub
		return other.isCollidingWith(clamp(other.getCenter(), pos, new Vector(pos.getX()+width, pos.getY()+height)));
	}

	private double clamp(double x, double min, double max) {
		// TODO Auto-generated method stub
		return x < min ? min : x > max ? max : x;
	}
	
	private Vector clamp(Vector v, Vector min, Vector max) {
		return new Vector(clamp(v.getX(), min.getX(), max.getX()), clamp(v.getY(), min.getY(), max.getY()));
	}

	@Override
	public boolean isCollidingWith(Vector p) {
		return (p.getX() > pos.getX() && p.getX() < pos.getX()+width && p.getY() > pos.getY() && p.getY() < pos.getY() + height);
	}

	@Override
	public void drawCollider(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.getGraphics().setColor(Collider.DRAWCOLOR);
		screen.getGraphics().drawRect((int)pos.getX(), (int)pos.getY(), (int)width, (int)height);
	}

	@Override
	public boolean isCollidingWith(BoxCollider other) {
        return ((width+pos.getX() < pos.getX() || width+pos.getX() > other.getPos().getX()) &&
                (height+pos.getY() < pos.getY() || height+pos.getY() > other.getPos().getY()) &&
                (other.getWidth()+other.getPos().getX() < other.getPos().getX() || other.getWidth()+other.getPos().getX() > pos.getX()) &&
                (other.getHeight()+other.getPos().getY() < other.getPos().getY() || other.getHeight()+other.getPos().getY() > pos.getY()));
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}



}
