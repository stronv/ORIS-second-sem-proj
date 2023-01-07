package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upPressed1, downPressed1, leftPressed1, rightPressed1;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) { upPressed = true; }
        if (code == KeyEvent.VK_A) { leftPressed = true; }
        if (code == KeyEvent.VK_S) { downPressed = true; }
        if (code == KeyEvent.VK_D) { rightPressed = true; }


        if (code == KeyEvent.VK_UP) { upPressed1 = true; }
        if (code == KeyEvent.VK_LEFT) { leftPressed1 = true; }
        if (code == KeyEvent.VK_DOWN) { downPressed1 = true; }
        if (code == KeyEvent.VK_RIGHT) { rightPressed1 = true; }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) { upPressed = false; }
        if (code == KeyEvent.VK_A) { leftPressed = false; }
        if (code == KeyEvent.VK_S) { downPressed = false; }
        if (code == KeyEvent.VK_D) { rightPressed = false; }

        if (code == KeyEvent.VK_UP) { upPressed1 = false; }
        if (code == KeyEvent.VK_LEFT) { leftPressed1 = false; }
        if (code == KeyEvent.VK_DOWN) { downPressed1 = false; }
        if (code == KeyEvent.VK_RIGHT) { rightPressed1 = false; }
    }
}
