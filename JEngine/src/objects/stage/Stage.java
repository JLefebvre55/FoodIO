package objects.stage;

import java.awt.Color;
import java.util.ArrayList;

import main.rendering.FrameRenderer;
import objects.RenderObject;

public abstract class Stage {

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
