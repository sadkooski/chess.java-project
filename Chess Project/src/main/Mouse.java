package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    public int x, y;
    public boolean pressed;
    
    /// Metoda obsługująca zdarzenie wciśnięcia przycisku myszy.
    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
    }

    /// Metoda obsługująca zdarzenie puszczenia przycisku myszy.
    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    /// Metoda obsługująca zdarzenie przeciągania myszy.
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /// Metoda obsługująca zdarzenie ruchu myszy.
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
