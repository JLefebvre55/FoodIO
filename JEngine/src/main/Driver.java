package main;


import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import objects.*;
import objects.UILayer.UILayerID;
import screen.*;

/**
 * Starts the thread, makes the window, registers the objects, runs the updates.
 * @author jaydenlefebvre
 * TODO scale? i.e. smaller screen; objects are shrunk to scale
 * TODO UI layers
 */
public class Driver implements Runnable, Engine{

	//Screen vars
	private static double aspectratio = 16.0/9.0;
	private static int width = 1280, height = (int)(width/aspectratio);
	private static String title = "JGraphics";
	private Window window;
	private Color bgcolor = Color.LIGHT_GRAY;

	//Runtime vars
	private boolean running;
	private static final long FUPDATE_NLENGTH = 10000000;
	private Thread thread;

	//All renderable objects
	private ArrayList<UILayer> layers = new ArrayList<UILayer>();

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
			update(new FrameRenderer(width, height, bgcolor));

			//frames generated increment
			fps++;

			//Slow update
			if(System.currentTimeMillis() - lastSlowUpdate >= 1000) {
				//Set screen head, reset counter
				window.getFrame().setTitle(title+"\t"+fps+"FPS");
				lastSlowUpdate = System.currentTimeMillis();
				fps = 0;
			}
		}
	}

	@Override
	public void update(FrameRenderer screen) {
		//Renders each layer in order
		for(int i = 0; i < UILayerID.size(); i++) {
			for(UILayer layer : layers) {
				if(layer.getLayerID().ordinal() == i) {
					for(Renderable e : layer) {
						e.update(screen);
					}
					break;
				}
			}
		}
		BufferStrategy bs = window.getCanvas().getBufferStrategy();
		bs.getDrawGraphics().drawImage(screen.getFrame(), 0, 0, width, height, null);
		bs.show();
	}

	@Override
	public void fixedUpdate() {
		for(UILayer layer : layers) {
			for(Renderable i : layer) {
				i.fixedUpdate();
			}
		}
	}

	/**
	 * Initialize necessary objects and variables.
	 */
	private void init() {
		running = true;
		window = new Window(title, width, height);
		window.getCanvas().createBufferStrategy(3);

		//Flush out some frames before we make it visible
		flushFrames();
		window.getFrame().setVisible(true);

		//Set up layering and registry
		for(int i = 0; i < UILayerID.size(); i++) {
			layers.add(new UILayer(UILayerID.getLayerID(i)));
		}
	}

	/**
	 * Run 4 frames through the buffer
	 */
	private void flushFrames() {
		for(int i = 0; i < 4; i++) {
			update(new FrameRenderer(width, height, bgcolor));
		}
	}

	/**
	 * Register a renderable object to the update list
	 * @param e - object
	 * @param id - ui layer to render on
	 */
	public void registerObject(Renderable e, UILayerID id) {
		for(UILayer layer : layers) {
			if(layer.getLayerID().equals(id)) {
				layer.add(e);
				break;
			}
		}
	}

}
