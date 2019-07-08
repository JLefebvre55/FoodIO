package screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import geometry.Vector;
import objects.ImageObject;

/**
 * Renderer for screen frames. ALL update calls must use this.
 * @author jaydenlefebvre
 * TODO separate thread for graphics
 */
public class FrameRenderer {
	
	private BufferedImage frame;
	private Graphics2D frameg;

	/**
	 * Makes a renderer without bg
	 * @param width in px
	 * @param height in px
	 */
	public FrameRenderer(int width, int height) {
		this(width, height, Color.WHITE);
	}

	/**
	 * Makes a renderer with bg
	 * @param width in px
	 * @param height in px
	 */
	public FrameRenderer(int width, int height, Color bgcolor) {
		this.frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		frameg = frame.createGraphics();
		frameg.setColor(bgcolor);
		frameg.fillRect(0, 0, width, height);
	}

	/**
	 * Renders an image to a location on screen
	 * @param image
	 * @param x
	 * @param y
	 */
	public void renderImage(BufferedImage image, Vector pos) {
		frameg.drawImage(image, (int)pos.getX(), (int)pos.getY(), null);
	}

	/**
	 * Get rendered frame
	 * @return rendered frame
	 */
	public BufferedImage getFrame() {
		return frame;
	}
	
	/**
	 * Get the drawing object
	 * @return
	 */
	public Graphics2D getGraphics() {
		return frameg;
	}
	
	public Vector getCenter() {
		return new Vector(frame.getWidth()/2, frame.getHeight()/2);
	}
}