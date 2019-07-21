package objects.entities;

import components.collision.BoxCollider;
import components.collision.Collider;
import components.collision.CollisionDetection;
import main.geometry.Vector;
import main.rendering.RenderLayer.LayerID;
import objects.AnimatedObject;

public class Coin extends AnimatedObject implements CollisionDetection{
	private static SpriteSheet sheet = new SpriteSheet("JEngine/coin.png", 12, 12);
	public static final double MASS = 30;

	public Coin(Vector pos) {
		super(pos, 100, 0, sheet.getSprites(), 3, LayerID.ITEMS);
		addComponent(new BoxCollider(this, new Vector(3, 0), 12*3-6, 12*3));
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
