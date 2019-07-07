package main;

import screen.FrameRenderer;

/**
 * ALL renderable objects must utilize update to be updated by main class
 * @author jaydenlefebvre
 *
 */
public interface Renderable {

	/**
	 * Update an object's render to a frame.
	 * @param screen - Frame driver to render to
	 */
	public abstract void update(FrameRenderer screen);
	
	/**
	 * Update at a fixed rate. Used for physics and other fixed-delta calculations.
	 */
	public abstract void fixedUpdate();
	
}
