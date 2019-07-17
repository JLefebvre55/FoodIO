package objects.entities;

import java.awt.Color;
import java.util.Random;

import components.Keyboard;
import components.collision.CircleCollider;
import components.collision.Collider;
import components.collision.CollisionDetection;
import main.Driver;
import main.geometry.Vector;
import main.rendering.FrameRenderer;
import main.rendering.RenderLayer.LayerID;
import objects.RenderObject;

public class Player extends RenderObject implements CollisionDetection {
	
	public static final double SIZE_INCREMENT = 3, START_SPEED = 4;
	private Keyboard keyboard = new Keyboard();
	private double speed = START_SPEED, size = 10;
	private Color playerColor;
	
	public Player(Vector pos, Color c) {
		super(pos, LayerID.CHARACTERS);
		addComponent(new CircleCollider(this, size));
		addComponent(keyboard);
		playerColor = c;
	}
	
	public Player(Vector pos){
		this(pos, randomPlayerColor());
	}
	
	private static Color randomPlayerColor() {
		Random rand = new Random();
		float x1 = rand.nextFloat();
		float x2 = rand.nextFloat()/2f;
		float x3 = rand.nextFloat()/2f;
		
		int colorMain = (int)(Math.random()*3);
		switch (colorMain) {
			case 0:
				return new Color(x1, x2, x3).brighter();
			case 1:
				return new Color(x2, x1, x3).brighter();
			case 2:
				return new Color(x2, x3, x1).brighter();
			default:
				return Color.BLACK;
		}
	}
	
	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		if (keyboard.isMoveup()) {
			pos.setY(pos.getY() - speed);
		} else if (keyboard.isMovedown()) {
			pos.setY(pos.getY() + speed);
		}
		if (keyboard.isMoveleft()) {
			pos.setX(pos.getX() - speed);
		} else if (keyboard.isMoveright()) {
			pos.setX(pos.getX() + speed);
		}
		
		if (pos.getX() > Driver.getWidth() + size * 2) {
			pos.setX(-size * 2);
		} else if (pos.getX() < -size * 2) {
			pos.setX(Driver.getWidth() + size * 2);
		}
		
		if (pos.getY() > Driver.getHeight() + size * 2) {
			pos.setY(-size * 2);
		} else if (pos.getY() < -size * 2) {
			pos.setY(Driver.getHeight() + size * 2);
		}
		
	}
	
	public String toString() {
		return "Player";
	}
	
	@Override
	public void collisionDetected(Collider other) {
		if (other.getParent() instanceof Coin) {
			Driver.delete(other.getParent());
			if (Driver.DEBUG)
				System.out.println("Ka-ching!");
			size+= SIZE_INCREMENT;
			for (CircleCollider c : getComponentsByType(CircleCollider.class)) {
				c.setRadius(size);
			}
			speed=getSpeed(size);
		}
	}
	
	private double getSpeed(double size) {
		return Math.sqrt(60/size)+(START_SPEED-2.5);
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.getGraphics().setColor(playerColor);
		screen.getGraphics().fillOval((int)pos.getX(), (int)pos.getY(), (int)size*2, (int)size*2);
	}
	
}
