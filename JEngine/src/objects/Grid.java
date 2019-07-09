package objects;

import java.awt.Color;

import main.geometry.Vector;
import main.rendering.FrameRenderer;
import main.rendering.RenderLayer.LayerID;

public class Grid extends RenderObject {
	
	private int delta;
	private Color color;

	public Grid(int delta) {
		this(delta, Color.BLACK);
	}
	
	public Grid(int delta, Color color) {
		super(new Vector(0, 0), LayerID.BACKGROUND);
		this.delta = delta;
		this.color = color;
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.getGraphics().setColor(color);
		for(int x = delta; x < screen.getFrame().getWidth(); x+= delta) {
			screen.getGraphics().drawLine(x, 0, x, screen.getFrame().getHeight());
		}
		for(int y = delta; y < screen.getFrame().getHeight(); y+= delta) {
			screen.getGraphics().drawLine(0, y, screen.getFrame().getWidth(), y);
		}
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
		return "Grid "+super.toString();
	}

}
