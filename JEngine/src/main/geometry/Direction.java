package main.geometry;

/**
 * Enum for directions with radian equivalents
 * @author jaydenlefebvre
 *
 */
public enum Direction {
	UP(3*Math.PI/2), RIGHT(0), DOWN(Math.PI/2), LEFT(Math.PI);

	private double radians;
	
	private Direction(double radians) {
		this.radians = radians;
	}
	
	private Direction() {
	}
	
	public Direction inverse(){
		switch(this) {
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		}
		return null;
	}
	
	public double getRadians() {
		return radians;
	}
}
