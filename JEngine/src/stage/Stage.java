package stage;

import java.awt.Color;
import java.util.ArrayList;

import main.Renderable;
import objects.RenderObject;
import screen.FrameRenderer;

public abstract class Stage implements Renderable{

	private ArrayList<RenderObject> objects = new ArrayList<RenderObject>();
	private String stageID;
	private boolean hasBackground;
	private Color bgcolor;

	public Stage(String stageID, Color bgcolor) {
		this.stageID = stageID;
		hasBackground = true;
		this.bgcolor = bgcolor;
	}
	
	public Stage(String stageID) {
		this.stageID = stageID;
		hasBackground = false;
	}

	public Stage() {
		this("Stage");
	}
	
	public Stage(Color bgcolor) {
		this("Stage", bgcolor);
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

	public FrameRenderer getFrameRenderer(int width, int height) {
		if(hasBackground) {
			return new FrameRenderer(width, height, bgcolor);
		} else {
			return new FrameRenderer(width, height);
		}
	}
}
