package main;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import components.Component;
import components.collision.Collider;
import components.collision.CollisionDetection;
import main.rendering.FrameRenderer;
import main.rendering.RenderLayer;
import main.rendering.RenderLayer.LayerID;
import main.rendering.Window;
import objects.RenderObject;
import objects.stage.GameTest;
import objects.stage.Stage;

/**
 * Starts the thread, makes the window, registers the objects, runs the updates.
 * @author jaydenlefebvre
 * TODO scale? i.e. smaller screen; objects are shrunk to scale
 * TODO UI layers
 * TODO toString methods!!
 * TODO Collision object??
 * TODO Comments (class and method), private/public rejig
 * TODO more debug messages
 * TODO how to integrate dual lists between HERE and STAGE? removing objects?
 */
public class Driver {

	//Screen vars
	private static double aspectratio = 16.0/9.0;
	private static int width = 1280, height = (int)(width/aspectratio);
	private static Window window;

	//Runtime vars
	private static boolean running;
	private static final long FUPDATE_NLENGTH = 10000000;
	public static final boolean DEBUG = false;
	private static Stage currentStage;
	private static ArrayList<RenderLayer> renderlayers = new ArrayList<RenderLayer>();

	/**
	 * Driver runtime code.
	 * Fixed update every FUPDATE_NLENGTH nanoseconds
	 * Update as often as teh boss lets you
	 * Slow update every second
	 */
	public static void main(String [] args) {
		init();
		//Time at last fixed update, frames generated per second, time at last slow update
		long lastFixedUpdate = 0, fps = 0, lastSlowUpdate = 0;

		//Central loop
		while(running) {

			//FUpdate
			if(System.nanoTime()-lastFixedUpdate >= FUPDATE_NLENGTH) {
				fixedUpdate();
				//Reset timer
				//TODO Reset before or after? ns loss?
				lastFixedUpdate = System.nanoTime();
			}

			//Update AFTER FUpdate
			update();

			//frames generated increment
			fps++;

			//Slow update
			if(System.currentTimeMillis() - lastSlowUpdate >= 1000) {
				//Set screen head, reset counter
				window.getFrame().setTitle(currentStage.getStageID()+"\t"+fps+"FPS");
				lastSlowUpdate = System.currentTimeMillis();
				fps = 0;
			}
		}
	}

	public static void update() {
		FrameRenderer renderer = currentStage.getFrameRenderer(width, height);
		//Renders each layer in order
		for (LayerID layer : LayerID.values()) {
			for(RenderObject e : currentStage.getObjectsByLayer(layer)){
				e.update(renderer);
			}
			if(layer.equals(LayerID.GUI)){
				for(RenderObject e : currentStage.getObjects()){
					for(Component c : e.getComponents()){
						c.update(renderer);
					}
				}
			}
		}
		BufferStrategy bs = window.getCanvas().getBufferStrategy();
		bs.getDrawGraphics().drawImage(renderer.getFrame(), 0, 0, width, height, null);
		bs.show();
	}

	public static void fixedUpdate() {

		ArrayList<RenderObject> fupdateobjects = currentStage.getObjects();

		for(RenderObject e : fupdateobjects) {
			e.fixedUpdate();
			e.fupdateComponents();
		}

		//Collider specific
		for(int i = 0; i < fupdateobjects.size(); i++) {
			ArrayList<Collider> c1 = fupdateobjects.get(i).getComponentsByType(Collider.class);
			for(int j = i+1; j < fupdateobjects.size(); j++) {
				ArrayList<Collider> c2 = fupdateobjects.get(j).getComponentsByType(Collider.class);
				for(Collider ca : c1) {
					for(Collider cb : c2) {
						//BY COLLIDER TYPE
						//TODO generic call, then sort instanceof
						if(ca.isCollidingWith(cb)){
							CollisionDetection x = (CollisionDetection)ca.getParent();
							CollisionDetection y = (CollisionDetection)cb.getParent();
							x.collisionDetected(cb);
							y.collisionDetected(ca);
							
							System.out.println(ca+" on "+ca.getParent()+" is colliding with "+cb+" on "+cb.getParent()+"!");
						}
					}
				}
			}

		}

		currentStage.refreshStage();
	}

	/**
	 * Initialize necessary objects and variables.
	 */
	private static void init() {
		running = true;

		//Set up layering and registry
		for(int i = 0; i < LayerID.size(); i++) {
			renderlayers.add(new RenderLayer(LayerID.getLayerID(i)));
		}

		window = new Window(width, height);
		window.getCanvas().createBufferStrategy(3);

		//Set up stage
		currentStage = new GameTest();

		//registerStage(currentStage);

		//Flush out some frames before we make it visible
		flushFrames();
		window.getFrame().setVisible(true);
	}

	/**
	 * Run 4 frames through the buffer
	 */
	private static void flushFrames() {
		for(int i = 0; i < 4; i++) {
			update();
		}
	}

	public static void delete(RenderObject e) {
		//Remove object
		currentStage.removeObject(e);

		if(DEBUG) {
			System.out.println("Removed object: "+e);
		}

	}

	/**
	 * @return the width
	 */
	public static int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public static int getHeight() {
		return height;
	}

	public static Window getWindow() {
		return window;
	}

}
