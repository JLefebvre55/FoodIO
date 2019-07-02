package objects;

import java.util.ArrayList;

/**
 * Placeholder class for a layer in rendering
 * @author jaydenlefebvre
 *
 */
public class UILayer extends ArrayList<Renderable>{
	
	public static enum UILayerID {
		BACKGROUND, OBJECTS, GUI;
		
		/**
		 * Get the ordinal of a specific layer
		 * @param id - The layer
		 * @return ordinal
		 */
		public static int getLayerIndex(UILayerID id) {
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
		public static UILayerID getLayerID(int i) {
			return UILayerID.values()[i];
		}
		
		public static int size() {
			return UILayerID.values().length;
		}
	}
	
	private UILayerID layer;
	
	public UILayer(UILayerID layer) {
		this.layer = layer;
	}
	
	public UILayerID getLayerID() {
		return layer;
	}
}