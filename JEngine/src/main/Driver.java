package main;


import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import components.Component;
import objects.RenderLayer;
import objects.RenderLayer.LayerID;
import objects.RenderObject;
import screen.FrameRenderer;
import screen.Window;
import stage.ImageTest;
import stage.Stage;

/**
 * Starts the thread, makes the window, registers the objects, runs the updates.
 * @author jaydenlefebvre
 * TODO scale? i.e. smaller screen; objects are shrunk to scale
 * TODO UI layers
 * TODO toString methods!!
 */
public class Driver implements Runnable, Renderable{

	//Screen vars
	private static double aspectratio = 16.0/9.0;
	private static int width = 1280, height = (int)(width/aspectratio);
	private Window window;
	private Color bgcolor = Color.GRAY;

	//Runtime vars
	private boolean running;
	private static final long FUPDATE_NLENGTH = 10000000;
	private Thread thread;
	private Stage currentStage;
	private ArrayList<RenderLayer> renderlayers = new ArrayList<RenderLayer>();
	private ArrayList<Renderable> fupdateobjects = new ArrayList<Renderable>();


	/**
	 * Starts the driver
	 * @param args
	 */
	public static void main(String [] args) {
		Driver d = new Driver();
		d.start();
	}

	/**
	 * Starts the driver thread.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	/**
	 * Driver runtime code.
	 * Fixed update every FUPDATE_NLENGTH nanoseconds
	 * Update as often as teh boss lets you
	 * Slow update every second
	 */
	public void run() {
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
			update(new FrameRenderer(width, height));

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

	@Override
	public void update(FrameRenderer screen) {
		//Renders each layer in order
		for(int i = 0; i < LayerID.size(); i++) {
			for(RenderLayer layer : renderlayers ) {
				//Find first layer
				if(layer.getLayerID().ordinal() == i) {
					//Do all
					for(Renderable e : layer) {
						e.update(screen);
					}
					break;
				}
			}
		}
		BufferStrategy bs = window.getCanvas().getBufferStrategy();
		bs.getDrawGraphics().drawImage(screen.getFrame(), 0, 0, width, height, null);
		bs.getDrawGraphics().dispose();
		bs.show();
	}

	@Override
	public void fixedUpdate() {
		currentStage.fixedUpdate();
	}

	/**
	 * Initialize necessary objects and variables.
	 */
	private void init() {
		running = true;

		//Set up layering and registry
		for(int i = 0; i < LayerID.size(); i++) {
			renderlayers.add(new RenderLayer(LayerID.getLayerID(i)));
		}

		//Set up stage
		currentStage = new ImageTest();
		registerStage(currentStage);

		window = new Window(currentStage.getStageID(), width, height);
		window.getCanvas().createBufferStrategy(3);

		//Flush out some frames before we make it visible
		flushFrames();
		window.getFrame().setVisible(true);
	}

	/**
	 * Run 4 frames through the buffer
	 */
	private void flushFrames() {
		for(int i = 0; i < 4; i++) {
			update(new FrameRenderer(width, height));
		}
	}

	/**
	 * Register a renderable object to the update list
	 * @param e - object
	 * @param id - ui layer to render on
	 */
	public void registerObject(RenderObject e) {
		for(RenderLayer layer : renderlayers) {
			if(layer.getLayerID().equals(e.getLayerID())) {
				layer.add(e);
				fupdateobjects.add(e);
				System.out.println("Registered an object: "+e);
				break;
			}
		}
		for(Component c : e.getComponents()) {
			if(c.isGraphical()) {
				for(RenderLayer layer : renderlayers) {
					if(layer.getLayerID().equals(LayerID.GUI)) {
						layer.add(c);
					}
				}
			}
		}
	}

	public void registerStage(Stage s) {
		ArrayList<RenderObject> o = s.getObjects();

		for(RenderObject e : o) {
			registerObject(e);
		}

		System.out.println("Registered stage: "+s.getStageID());
	}

}
