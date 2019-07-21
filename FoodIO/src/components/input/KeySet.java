package components.input;

import java.awt.event.KeyEvent;

public enum KeySet {
    PLAYERONE(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT), PLAYERTWO(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);

    public final int upkey, downkey, leftkey, rightkey;

    KeySet(int a, int b, int c, int d){
        upkey = a;
        downkey = b;
        leftkey = c;
        rightkey = d;
    }
}