package components.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import components.Component;
import main.Driver;
import main.rendering.FrameRenderer;

/**
* Keyboard for IO
* @author miguelweerasinghe
* @updatedby jaydenlefebvre
*
*/
public class Keyboard extends Component implements KeyListener{
	
	private boolean [] keys = new boolean[65535];

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

	}
}
