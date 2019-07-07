package geometry;

/**
 * Stores a 2D vector.
 * @author jaydenlefebvre
 * TODO Point child class??
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
	
	public Vector add(Vector v2) {
		return new Vector(this.x + v2.getX(), this.y + v2.getY());
	}
	
	public Vector subtract(Vector v2) {
		return new Vector(this.x - v2.getX(), this.y - v2.getY());
	}
	
	public Vector scale(double d){
		return new Vector(this.x*d, this.y*d);
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	public double getDistance(Vector v2) {
		// TODO Auto-generated method stub
		return Math.sqrt(Math.pow(x+v2.getX(), 2)+Math.pow(y+v2.getY(), 2));
	}
}
