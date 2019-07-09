package main.rendering;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Window object. Encompasses a JFrame and components to make it easier to use.
 * @author miguelweerasinghe
 * @updatedby jaydenlefebvre
 * TODO extend JFrame? add panels?
 *
 */
public class Window {
	private static JFrame frame;//OS-level equivalent window
	private int width, height;	//width and height of frame
	private Canvas canvas;	//Canvas is the tool for drawing graphics, i.e. shapes, lines
	private String title;	//title of frame

	/**
	 * Makes the window
	 * @param title
	 * @param width
	 * @param height
	 */
	public Window(String title, int width, int height){
		this.height = height;
		this.width = width;
		this.title = title;

		init();
	}

	/**
	 * Initializes all fields. Frame, canvas, and all dimensions and values. Frame starts hidden
	 */
	private void init() {
		//Frame setup
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(false);	//Unable to resize post-init
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Close frame when pressing platform's exit button

		//Canvas setup
		canvas = new Canvas();
		//Dimensions
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setPreferredSize(new Dimension(width, height));

		//Add necessary components to frame
		frame.add(canvas);
		frame.pack();
	}

	/**
	 * Returns the canvas element for drawing graphics
	 * @return the canvas
	 */
	public Canvas getCanvas(){
		return canvas;
	}

	/**
	 * Returns the frame of this window
	 * @return the frame
	 */
	public JFrame getFrame(){
		return frame;
	}


}