package objects;

import java.awt.Color;

import main.geometry.Vector;
import main.rendering.FrameRenderer;
import main.rendering.RenderLayer.LayerID;

/**
 * StringObject
 */
public class StringObject extends RenderObject{

    private String text;


    public StringObject(Vector pos, String text) {
        super(pos, LayerID.GUI);
        this.text = text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "String object \"" + getText() + "\"";
    }

    @Override
    public void update(FrameRenderer screen) {
        screen.getGraphics().setColor(Color.BLACK);
        screen.getGraphics().drawString(text, (int)(pos.getX()), (int)(pos.getY()));
    }


    @Override
    public void fixedUpdate() {
		
	}
    
}