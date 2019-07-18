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

/**
 * TODO add AI, test eating mechanic
 * TODO add second player, many players, networking?
 * TODO center name
 */
public class Player extends RenderObject implements CollisionDetection {
	
	public static final double START_SPEED = 4;
	private Keyboard keyboard = new Keyboard();
	private double mass = 50;
	private Color playerColor;
	private String name;
	
	public Player(Vector pos, Color c, String name) {
		super(pos, LayerID.CHARACTERS);
		addComponent(new CircleCollider(this, getRadius()));
		addComponent(keyboard);
		Driver.getWindow().getCanvas().addKeyListener(keyboard);
		playerColor = c;
		this.name = name;
	}
	
	public Player(Vector pos, String name){
		this(pos, randomPlayerColor(), name);
	}

	public Player(Vector pos){
		this(pos, "Unnamed");
	}
	
	/**
	 * Gets a random suitable player colour
	 * @return a randomized colour, leaning towards one of R,G,B. Nice and bright.
	 */
	private static Color randomPlayerColor() {
		Random rand = new Random();
		float x1 = rand.nextFloat();
		float x2 = rand.nextFloat()/3f;
		float x3 = rand.nextFloat()/3f;
		
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
			pos.setY(pos.getY() - getSpeed());
		} else if (keyboard.isMovedown()) {
			pos.setY(pos.getY() + getSpeed());
		}
		if (keyboard.isMoveleft()) {
			pos.setX(pos.getX() - getSpeed());
		} else if (keyboard.isMoveright()) {
			pos.setX(pos.getX() + getSpeed());
		}
		
		if (pos.getX() > Driver.getWidth() + getRadius() * 2) {
			pos.setX(-getRadius() * 2);
		} else if (pos.getX() < -getRadius() * 2) {
			pos.setX(Driver.getWidth() + getRadius() * 2);
		}
		
		if (pos.getY() > Driver.getHeight() + getRadius() * 2) {
			pos.setY(-getRadius() * 2);
		} else if (pos.getY() < -getRadius() * 2) {
			pos.setY(Driver.getHeight() + getRadius() * 2);
		}
		
	}
	
	public double getRadius() {
		return Math.sqrt(5*mass);
	}

	@Override
	public String toString() {
		return "Player \""+name+"\" (Mass: "+mass+")";
	}
	
	@Override
	public void collisionDetected(Collider other) {
		if (other.getParent() instanceof Coin) {
			Driver.delete(other.getParent());
			if (Driver.DEBUG)
				System.out.println("Ka-ching!");
			mass+= Coin.MASS;
			for (CircleCollider c : getComponentsByType(CircleCollider.class)) {
				c.setRadius(getRadius());
			}
		} else if(other.getParent() instanceof Player){
			Player enemy = (Player)other.getParent();
			if(mass - enemy.getMass() > 20){
				Driver.delete(enemy);
				System.out.println("MUNCH! "+this+" ate "+enemy);
				mass+= enemy.getMass();
			}
		}
	}
	
	private double getSpeed() {
		return Math.sqrt(60/mass)+(START_SPEED-2.5);
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.getGraphics().setColor(playerColor);
		screen.getGraphics().fillOval((int)pos.getX(), (int)pos.getY(), (int)getRadius()*2, (int)getRadius()*2);
		screen.getGraphics().setColor(Color.BLACK);
		screen.getGraphics().drawString(name, (int)(pos.getX()+getRadius()), (int)(pos.getY()+getRadius()));
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}
	
}
