package objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import geometry.Vector;
import objects.RenderLayer.LayerID;
import screen.FrameRenderer;

/**
 * Generic parent class for animated objects
 * @author jaydenlefebvre
 *
 */
public abstract class AnimatedObject extends ImageObject {

	protected long lastAnimation;
	protected long timeBetween;
	protected int currentIndex, initialIndex;
	protected ArrayList<BufferedImage> frames;

	/**
	 * Creates an animator
	 * @param timeBetween - time in ms between frames
	 * @param startIndex - first frame
	 * @param frames - All frames (linear x animation)
	 */
	public AnimatedObject(Vector pos, long timeBetween, int startIndex, ArrayList<BufferedImage> frames, LayerID renderlayer) {
		this(pos, timeBetween, startIndex, frames, 1, renderlayer);
	}
	
	public AnimatedObject(Vector pos, long timeBetween, int startIndex, ArrayList<BufferedImage> frames, double scale, LayerID renderlayer) {
		super(pos, frames.get(startIndex), renderlayer, scale);
		this.timeBetween = timeBetween;
		this.initialIndex = startIndex;
		this.currentIndex = startIndex;
		this.frames = frames;
	}

	protected void doAnimation() {
		if(System.currentTimeMillis() - lastAnimation >= timeBetween) {
			currentIndex = (currentIndex+1)%frames.size();
			srcimage = frames.get(currentIndex);
			lastAnimation = System.currentTimeMillis();
		}
	}
	
}
