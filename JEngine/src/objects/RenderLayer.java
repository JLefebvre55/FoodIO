package objects;

import java.util.ArrayList;

import main.Renderable;

/**
 * Placeholder class for a layer in rendering
 * @author jaydenlefebvre
 *
 */
public class RenderLayer extends ArrayList<Renderable>{
	
	public static enum LayerID {
		BACKGROUND, OBJECTS, GUI;
		
		/**
		 * Get the ordinal of a specific layer
		 * @param id - The layer
		 * @return ordinal
		 */
		public static int getLayerIndex(LayerID id) {
			return id.ordinal();
		}
		
		/**
		 * Get the ordinal of this layer
		 * @return ordinal
		 */
		public int getLayerIndex() {
			return getLayerIndex(this);
		}
		
		/**
		 * Get a layer from an ordinal
		 * @param i - ordinal
		 * @return the layer
		 */
		public static LayerID getLayerID(int i) {
			return LayerID.values()[i];
		}
		
		public static int size() {
			return LayerID.values().length;
		}
	}
	
	private LayerID layer;
	
	public RenderLayer(LayerID layer) {
		this.layer = layer;
	}
	
	public LayerID getLayerID() {
		return layer;
	}
}