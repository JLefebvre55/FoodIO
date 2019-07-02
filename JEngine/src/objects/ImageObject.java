package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import geometry.Vector;
import screen.FrameRenderer;

public class ImageObject extends Renderable {
	
	private BufferedImage image;

	/**
	 * Makes image object based on position and source image
	 * @param pos
	 * @param image
	 */
	public ImageObject(Vector pos, BufferedImage image) {
		super(pos);
		this.image = image;
	}
	
	/**
	 * Makes image object based on position and image url
	 * @param pos
	 * @param url
	 * @throws IOException 
	 */
	public ImageObject(Vector pos, String url) throws IOException {
		this(pos, ImageIO.read(new File(url)));
	}

	@Override
	public void update(FrameRenderer screen) {
		screen.renderImageObject(this);
	}

	@Override
	public void fixedUpdate() {

	}
	
	public BufferedImage getImage() {
		return image;
	}

}
