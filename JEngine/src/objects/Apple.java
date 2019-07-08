package objects;

import collision.CircleCollider;
import collision.Collider;
import collision.CollisionDetection;
import components.Keyboard;
import geometry.Vector;
import main.Driver;
import objects.RenderLayer.LayerID;

public class Apple extends ImageObject implements CollisionDetection{

	private Keyboard keyboard = new Keyboard();
	private double speed = 2;
	
	public Apple(Vector pos) {
		super(pos, "apple.png", LayerID.OBJECTS, 0.05);
		addComponent(new CircleCollider(this, 53));
		addComponent(keyboard);
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
		return "Apple";
	}

	@Override
	public void collisionDetected(Collider other) {
		if(other.getParent() instanceof Coin) {
			Driver.delete(other.getParent());
			if(Driver.DEBUG)
				System.out.println("Ka-ching!");
		}
	}

}
