package objects.entities;

import components.Keyboard;
import components.collision.CircleCollider;
import components.collision.Collider;
import components.collision.CollisionDetection;
import main.Driver;
import main.geometry.Vector;
import main.rendering.RenderLayer.LayerID;
import objects.ImageObject;

public class Apple extends ImageObject implements CollisionDetection{

	private Keyboard keyboard = new Keyboard();
	private double speed = 2, radius = 50;

	public Apple(Vector pos) {
		super(pos, "apple.png", LayerID.CHARACTERS, 0.05);
		addComponent(new CircleCollider(this, radius+3));
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

		if(pos.getX() > Driver.getWidth()+radius*2) {
			pos.setX(-radius*2);
		} else if(pos.getX() < -radius*2){
			pos.setX(Driver.getWidth()+radius*2);
		}

		if(pos.getY() > Driver.getHeight()+radius*2) {
			pos.setY(-radius*2);
		} else if(pos.getY() < -radius*2){
			pos.setY(Driver.getHeight()+radius*2);
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
