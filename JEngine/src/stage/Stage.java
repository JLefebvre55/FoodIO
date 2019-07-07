package stage;

import java.util.ArrayList;

import main.Renderable;
import objects.RenderObject;
import screen.FrameRenderer;

public abstract class Stage implements Renderable{

	private ArrayList<RenderObject> objects = new ArrayList<RenderObject>();
	private String stageID;
	
	public Stage(String stageID) {
		this.stageID = stageID;
	}
	
	public Stage() {
		this("Stage");
	}
	
	public ArrayList<RenderObject> getObjects(){
		return objects;
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		for(RenderObject e : objects) {
			e.update(screen);
		}
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		for(RenderObject e : objects) {
			e.fixedUpdate();
			e.fupdateComponents();
		}
	}

	public String getStageID() {
		return stageID;
	}
	
	public void addObject(RenderObject o) {
		objects.add(o);
	}
}
