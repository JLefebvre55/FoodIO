package objects;

import collision.CircleCollider;
import collision.Collider;
import collision.CollisionDetection;
import geometry.Vector;
import objects.RenderLayer.LayerID;

public class Coin extends AnimatedObject implements CollisionDetection{
	
	private static SpriteSheet sheet = new SpriteSheet("coin.png", 12, 12);

	public Coin(Vector pos) {
		super(pos, 100, 0, sheet.getSprites(), 3, LayerID.OBJECTS);
		addComponent(new CircleCollider(this, 6*3));
	}

	@Override
	public void collisionDetected(Collider other) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Coin";
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		doAnimation();
	}

}
