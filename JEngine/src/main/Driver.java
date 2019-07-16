package main;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import components.Component;
import components.Keyboard;
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
 * TODO change all static, maybe framerenderer as well
 * TODO Collision object??
 * TODO Comments (class and method), private/public rejig
 * TODO more debug messages
 */
public class Driver {

	//Screen vars
	private static double aspectratio = 16.0/9.0;
	private static int width = 1280, height = (int)(width/aspectratio);
	private static Window window;

	//Runtime vars
	private static boolean running;
	private static final long FUPDATE_NLENGTH = 10000000;
	public static final boolean DEBUG = true;
	private static Stage currentStage;
	private static ArrayList<RenderLayer> renderlayers = new ArrayList<RenderLayer>();
	private static ArrayList<RenderObject> fupdateobjects = new ArrayList<RenderObject>();


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
		for(int i = 0; i < LayerID.size(); i++) {
			for(RenderLayer layer : renderlayers ) {
				//Find first layer
				if(layer.getLayerID().ordinal() == i) {
					//Do all
					for(Renderable e : layer) {
						e.update(renderer);
					}
					break;
				}
			}
		}
		BufferStrategy bs = window.getCanvas().getBufferStrategy();
		bs.getDrawGraphics().drawImage(renderer.getFrame(), 0, 0, width, height, null);
		bs.show();
	}

	public static void fixedUpdate() {
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
							CollisionDetection c = (CollisionDetection)ca.getParent();
							c.collisionDetected(cb);
							System.out.println(ca+" on "+ca.getParent()+" is colliding with "+cb+" on "+cb.getParent()+"!");
						}
					}
				}
			}

		}
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

		//Set up stage
		currentStage = new GameTest();

		window = new Window(currentStage.getStageID(), width, height);
		window.getCanvas().createBufferStrategy(3);

		registerStage(currentStage);

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

	/**
	 * Register a renderable object to the update list
	 * @param e - object
	 * @param id - ui layer to render on
	 */
	public static void registerObject(RenderObject e) {
		for(RenderLayer layer : renderlayers) {
			if(layer.getLayerID().equals(e.getLayerID())) {
				layer.add(e);
				fupdateobjects.add(e);
				if(DEBUG)
					System.out.println("Registered an object: "+e);
				break;
			}
		}
		for(Component c : e.getComponents()) {
			if(c instanceof Keyboard) {
				window.getCanvas().addKeyListener((Keyboard)c);
			}
			if(c.isGraphical()) {
				for(RenderLayer layer : renderlayers) {
					if(layer.getLayerID().equals(LayerID.GUI)) {
						layer.add(c);
						if(DEBUG)
							System.out.println("- Registered a component: "+c);
					}
				}
			}
		}
	}

	public static void registerStage(Stage s) {
		ArrayList<RenderObject> o = s.getObjects();

		for(RenderObject e : o) {
			registerObject(e);
		}
		if(DEBUG)
			System.out.println("Registered stage: "+s.getStageID());
	}

	public static void delete(RenderObject e) {
		//Remove object
		fupdateobjects.remove(e);
		for(RenderLayer layer : renderlayers) {
			if(layer.getLayerID().equals(e.getLayerID())) {
				layer.remove(e);
				break;
			}
		}
		if(DEBUG) {
			System.out.println("Removed object: "+e);
		}

		for(RenderLayer layer : renderlayers) {
			if(layer.getLayerID().equals(LayerID.GUI)) {
				for(Component c : e.getComponents()) {
					layer.remove(c);
					if(DEBUG) {
						System.out.println("- Removed component: "+c);
					}
				}
			}
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

}
