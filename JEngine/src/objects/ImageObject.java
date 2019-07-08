package objects;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import geometry.Vector;
import objects.RenderLayer.LayerID;
import screen.FrameRenderer;

public abstract class ImageObject extends RenderObject {

	protected BufferedImage srcimage;
	protected double scale;

	/**
	 * Makes image object based on position and source image
	 * @param pos
	 * @param image
	 */
	public ImageObject(Vector pos, BufferedImage image, LayerID layerid) {
		this(pos, image, layerid, 1);
	}

	public ImageObject(Vector pos, String url, LayerID layerid) {
		this(pos, url, layerid, 1);
	}

	/**
	 * Makes image object based on position and image url
	 * @param pos
	 * @param url
	 */
	public ImageObject(Vector pos, BufferedImage image, LayerID layerid, double scale) {
		super(pos, layerid);

		this.srcimage = image;
		this.scale = scale;

	}

	public static BufferedImage scaleImage(BufferedImage image, double scale) {
		// TODO Auto-generated method stub
		scale = scale <=0 ? 1 : scale;
		AffineTransform resize = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp op = new AffineTransformOp (
				resize,
				AffineTransformOp.TYPE_BICUBIC);
		return op.filter(image, null);
	}

	public ImageObject(Vector pos, String url, LayerID layerid, double scale) {
		super(pos, layerid);
		this.scale = scale;
		try {
			this.srcimage = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(FrameRenderer screen) {
		screen.getGraphics().drawImage(getImage(), (int)pos.getX(), (int)pos.getY(), null);
	}

	public BufferedImage getSourceImage() {
		return srcimage;
	}

	public BufferedImage getImage() {
		return scaleImage(srcimage, scale);
	}

	public String toString() {
		return "Image "+super.toString();
	}

}
