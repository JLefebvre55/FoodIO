package objects;

import java.awt.Color;

import collision.Collider;
import collision.CollisionDetection;
import geometry.Vector;

public class Coin extends Ball implements CollisionDetection{

	public Coin(Vector pos) {
		super(pos, Color.YELLOW, 10);
	}

	@Override
	public void collisionDetected(Collider other) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Coin";
	}

}
