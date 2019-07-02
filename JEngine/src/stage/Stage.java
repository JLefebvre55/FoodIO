package stage;

import java.util.ArrayList;

import objects.Renderable;

public abstract class Stage {

	private String name;
	private ArrayList<Renderable> objects = new ArrayList<Renderable>();
	
	public ArrayList<Renderable> getObjects() {
		return objects;
	}

}
