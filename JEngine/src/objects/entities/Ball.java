package objects.entities;

import java.awt.Color;

import components.collision.CircleCollider;
import main.geometry.Vector;
import main.rendering.FrameRenderer;
import main.rendering.RenderLayer.LayerID;
import objects.RenderObject;

public class Ball extends RenderObject {

	private Color color;
	private double radius;

	public Ball(Vector pos, Color color, double radius) {
		super(pos, LayerID.CHARACTERS);
		addComponent(new CircleCollider(this, radius));
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
	}

	public String toString() {
		return "Ball "+super.toString();
	}

}
