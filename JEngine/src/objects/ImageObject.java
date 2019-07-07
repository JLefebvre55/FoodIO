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

	private BufferedImage image;

	/**
	 * Makes image object based on position and source image
	 * @param pos
	 * @param image
	 */
	public ImageObject(Vector pos, BufferedImage image, LayerID layerid) {
		super(pos, layerid);
		this.image = image;
	}

	public ImageObject(Vector pos, String url, LayerID layerid) {
		super(pos, layerid);
		try {
			this.image = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Makes image object based on position and image url
	 * @param pos
	 * @param url
	 */
	public ImageObject(Vector pos, BufferedImage image, LayerID layerid, double scale) {
		super(pos, layerid);
		scale = scale > 1 ? 1 : scale <=0 ? 0 : scale;
		AffineTransform resize = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp op = new AffineTransformOp (
				resize,
				AffineTransformOp.TYPE_BICUBIC);

		this.image = op.filter(image, null);

	}

	public ImageObject(Vector pos, String url, LayerID layerid, double scale) {
		super(pos, layerid);

		try {
			BufferedImage img = ImageIO.read(new File(url));
			scale = scale > 1 ? 1 : scale <=0 ? 0 : scale;
			AffineTransform resize = AffineTransform.getScaleInstance(scale, scale);
			AffineTransformOp op = new AffineTransformOp (
					resize,
					AffineTransformOp.TYPE_BICUBIC);
			this.image = op.filter(img, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ImageObject(Vector pos, String url, LayerID layerid, int sizex, int sizey) {
		super(pos, layerid);
		try {
			BufferedImage img = ImageIO.read(new File(url));
			sizex = sizex < 1 ? 1 : sizex;
			sizey = sizey < 1 ? 1 : sizey;
			AffineTransform resize = AffineTransform.getScaleInstance((double)sizex/img.getWidth(), (double)sizey/img.getHeight());
			AffineTransformOp op = new AffineTransformOp (
					resize,
					AffineTransformOp.TYPE_BICUBIC);

			this.image = op.filter(img, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ImageObject(Vector pos, BufferedImage image, LayerID layerid, int sizex, int sizey) {
		super(pos, layerid);
		sizex = sizex < 1 ? 1 : sizex;
		sizey = sizey < 1 ? 1 : sizey;
		AffineTransform resize = AffineTransform.getScaleInstance((double)sizex/image.getWidth(), (double)sizey/image.getHeight());
		AffineTransformOp op = new AffineTransformOp (
				resize,
				AffineTransformOp.TYPE_BICUBIC);
		this.image = op.filter(image, null);

	}

	@Override
	public void update(FrameRenderer screen) {
		screen.renderImageObject(this);
	}

	public BufferedImage getImage() {
		return image;
	}

	public String toString() {
		return "Image "+super.toString();
	}

}
