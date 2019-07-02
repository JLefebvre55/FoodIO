package geometry;

/**
 * Stores a 2D vector.
 * @author jaydenlefebvre
 *
 */
public class Vector {

	protected double x, y;

	/**
	 * Makes a vector using x and y components
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Basic trig, makes a vector using an angle and a sum (hypotenuse)
	 * @param sum
	 * @param a
	 */
	public Vector(double sum, Angle a) {
		this(sum*Math.sin(a.getAngle()), sum*Math.cos(a.getAngle()));
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	public void add(Vector v2) {
		this.x += v2.getX();
		this.y += v2.getY();
	}
	
	public Vector sub(double div) {
		return new Vector(x/div, y/div);
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
