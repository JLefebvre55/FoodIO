package objects.entities;

import components.Keyboard;
import components.collision.CircleCollider;
import components.collision.Collider;
import components.collision.CollisionDetection;
import main.Driver;
import main.geometry.Direction;
import main.geometry.Vector;
import main.rendering.RenderLayer.LayerID;
import objects.ImageObject;

public class Apple extends ImageObject implements CollisionDetection, Moving{

	private Keyboard keyboard = new Keyboard();
	private double speed = 2, radius = 50;
	private Direction lastMove = null;

	public Apple(Vector pos) {
		super(pos, "apple.png", LayerID.CHARACTERS, 0.05);
		addComponent(new CircleCollider(this, radius+3));
		addComponent(keyboard);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		if(keyboard.isMove()) {
			move(keyboard.getMove());
			lastMove = keyboard.getMove();
		}

		//Screen loop
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

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		switch(direction) {
		case UP:
			pos.setY(pos.getY()-speed);
			break;
		case DOWN:
			pos.setY(pos.getY()+speed);
			break;
		case LEFT:
			pos.setX(pos.getX()-speed);
			break;
		case RIGHT:
			pos.setX(pos.getX()+speed);
			break;
		}
	}
	
	public Direction getLastMove() {
		return lastMove;
	}

}
