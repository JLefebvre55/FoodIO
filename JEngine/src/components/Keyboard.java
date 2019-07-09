package components;

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
public class Keyboard extends Component implements KeyListener{

	private boolean [] keys = new boolean[65536];//keys 
	private boolean arrowup, arrowdown, arrowleft, arrowright, moveup, movedown, moveleft, moveright, action;//boolean for if pressed or not

	public Keyboard() {
		super(null, false);
	}

	/**
	 * A key has been pressed down, is called by KeyListener attached to window
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		if(Driver.DEBUG)
			System.out.println(KeyEvent.getKeyText(e.getKeyCode())+" key pressed");
		//keys been pressed so its true
	}

	/**
	 * A key has been released, is called by KeyListener attached to window
	 */
	public void keyReleased(KeyEvent e) {
		//released so key pressed is false
		keys[e.getKeyCode()] = false;
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
		arrowup = keys[KeyEvent.VK_UP];
		arrowdown = keys[KeyEvent.VK_DOWN];
		arrowleft = keys[KeyEvent.VK_LEFT];
		arrowright = keys[KeyEvent.VK_RIGHT];
		moveright = keys[KeyEvent.VK_D];
		moveleft = keys[KeyEvent.VK_A];
		moveup = keys[KeyEvent.VK_W];
		movedown = keys[KeyEvent.VK_S];
		action = keys[KeyEvent.VK_SPACE];
	}

	/**
	 * @return the keys
	 */
	public boolean[] getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}

	/**
	 * @return the arrowup
	 */
	public boolean isArrowup() {
		return arrowup;
	}

	/**
	 * @return the arrowdown
	 */
	public boolean isArrowdown() {
		return arrowdown;
	}

	/**
	 * @return the arrowleft
	 */
	public boolean isArrowleft() {
		return arrowleft;
	}

	/**
	 * @return the arrowright
	 */
	public boolean isArrowright() {
		return arrowright;
	}

	/**
	 * @return the moveup
	 */
	public boolean isMoveup() {
		return moveup;
	}

	/**
	 * @return the movedown
	 */
	public boolean isMovedown() {
		return movedown;
	}

	/**
	 * @return the moveleft
	 */
	public boolean isMoveleft() {
		return moveleft;
	}

	/**
	 * @return the moveright
	 */
	public boolean isMoveright() {
		return moveright;
	}

	/**
	 * @return the action
	 */
	public boolean isAction() {
		return action;
	}
	
	

}
