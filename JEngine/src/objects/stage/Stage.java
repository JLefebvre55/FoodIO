package objects.stage;

import java.awt.Color;
import java.util.ArrayList;

import main.Driver;
import main.geometry.Vector;
import main.rendering.FrameRenderer;
import main.rendering.RenderLayer.LayerID;
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
	
	public <T extends RenderObject> ArrayList<T> getObjectsByType(Class<T> cls){
		ArrayList<T> temp = new ArrayList<T>();
		for(RenderObject c : objects) {
			if(cls.isInstance(c)) {
				temp.add(cls.cast(c));
			}
		}
		return temp;
	}
	
	public FrameRenderer getFrameRenderer(int width, int height) {
		if(hasBackground) {
			return new FrameRenderer(width, height, bgcolor);
		} else {
			return new FrameRenderer(width, height);
		}
	}
	
	protected Vector randomVectorInBounds() {
		return new Vector(Math.random()*Driver.getWidth(), Math.random()*Driver.getHeight());
	}
	
	public abstract void refreshStage();
	
	public void removeObject(RenderObject e) {
		
		objects.remove(e);
		
	}

	public ArrayList<RenderObject> getObjectsByLayer(LayerID layer){
		ArrayList<RenderObject> temp = new ArrayList<RenderObject>();
		for (RenderObject e : objects) {
			if(e.getLayerID().equals(layer)){
				temp.add(e);
			}
		}
		return temp;
	}
}
