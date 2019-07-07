package objects;

import java.awt.Color;

import collision.CircleCollider;
import geometry.Vector;
import objects.RenderLayer.LayerID;
import screen.FrameRenderer;

public class Ball extends RenderObject {
	
	private Color color;
	private double radius;

	public Ball(Vector pos, Color color, double radius) {
		super(pos, LayerID.OBJECTS);
		addComponent(new CircleCollider(this, radius));
		this.color = color;
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.getGraphics().setColor(color);
		screen.getGraphics().fillOval((int)pos.getX(), (int)pos.getY(), (int)radius*2, (int)radius*2);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Ball "+super.toString();
	}

}
