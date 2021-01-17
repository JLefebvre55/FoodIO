package components.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Driver;
import main.rendering.FrameRenderer;

/**
* Keyboard for IO
* @author miguelweerasinghe
* @updatedby jaydenlefebvre
*
*/
public class PlayerKeyboard extends Keyboard implements KeyListener{
	
	private boolean up, down, left, right;
	private final int upkey, downkey, leftkey, rightkey; //boolean for if pressed or not

	public PlayerKeyboard(int upkey, int downkey, int leftkey, int rightkey) {
		this.upkey = upkey;
		this.downkey = downkey;
		this.leftkey = leftkey;
		this.rightkey = rightkey;
	}
	

	public PlayerKeyboard(KeySet ks){
		this(ks.upkey, ks.downkey, ks.leftkey, ks.rightkey);
	}
	
	/**
	* A key has been pressed down, is called by KeyListener attached to window
	*/
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == upkey){
			up = true;
		} else if(e.getKeyCode() == downkey){
			down = true;
		} else if(e.getKeyCode() == leftkey){
			left = true;
		} else if(e.getKeyCode() == rightkey){
			right = true;
		}
		if(Driver.DEBUG)
		System.out.println(KeyEvent.getKeyText(e.getKeyCode())+" key pressed");
		//keys been pressed so its true
	}
	
	/**
	* A key has been released, is called by KeyListener attached to window
	*/
	public void keyReleased(KeyEvent e) {
		//released so key pressed is false
		if(e.getKeyCode() == upkey){
			up = false;
		} else if(e.getKeyCode() == downkey){
			down = false;
		} else if(e.getKeyCode() == leftkey){
			left = false;
		} else if(e.getKeyCode() == rightkey){
			right = false;
		}
		if(Driver.DEBUG)
		System.out.println(KeyEvent.getKeyText(e.getKeyCode())+" key released");
	}
	
	//Needed by interface
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return the up
	 */
	public boolean isMoveUp() {
		return up;
	}

	/**
	 * @return the down
	 */
	public boolean isMoveDown() {
		return down;
	}

	/**
	 * @return the left
	 */
	public boolean isMoveLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	public boolean isMoveRight() {
		return right;
	}
}
