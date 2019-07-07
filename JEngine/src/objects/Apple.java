package objects;

import collision.CircleCollider;
import geometry.Vector;
import objects.RenderLayer.LayerID;

public class Apple extends ImageObject {

	public Apple(Vector pos) {
		super(pos, "apple.png", LayerID.OBJECTS, 50, 50);
		addComponent(new CircleCollider(this, 26));
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return "Apple "+super.toString();
	}

}
