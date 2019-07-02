package geometry;

public class Angle {

	private double angle;
	
	public Angle(double radians) {
		if(Math.abs(radians) > 2*Math.PI) {
			radians %= 2*Math.PI;
		}
		if(radians < 0) {
			radians += 2*Math.PI;
		}
		
		this.angle = radians;
	}
	
	public Angle(double x1, double y1, double x2, double y2) {
		this(Math.atan((y2-y1)/(x2-x1)));
	}
	
	public double getAngle() {
		return angle;
	}
	
}
