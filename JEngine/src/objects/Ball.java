package objects;

import java.awt.Color;

import collision.CircleCollider;
import components.Keyboard;
import geometry.Vector;
import objects.RenderLayer.LayerID;
import screen.FrameRenderer;

public class Ball extends RenderObject {
	
	private Color color;
	private double radius;
	private Keyboard keyboard = new Keyboard();
	private double speed = 1;
	
	public Ball(Vector pos, Color color, double radius) {
		super(pos, LayerID.OBJECTS);
		addComponent(new CircleCollider(this, radius));
		addComponent(keyboard);
		this.color = color;
		this.radius = radius;
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
		if(keyboard.isMoveup()) {
			pos.setY(pos.getY()-speed);
		} else if(keyboard.isMovedown()) {
			pos.setY(pos.getY()+speed);
		}
		if(keyboard.isMoveleft()) {
			pos.setX(pos.getX()-speed);
		} else if(keyboard.isMoveright()) {
			pos.setX(pos.getX()+speed);
		}
	}
	
	public String toString() {
		return "Ball "+super.toString();
	}

}
