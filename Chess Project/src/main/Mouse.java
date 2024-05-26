package main;

import java.awt.event.MouseAdapter;                                             // Importuje klasę MouseAdapter z pakietu java.awt.event, która umożliwia obsługę zdarzeń myszy.
import java.awt.event.MouseEvent;                                               // Importuje klasę MouseEvent z pakietu java.awt.event, która reprezentuje zdarzenia związane z myszą.

public class Mouse extends MouseAdapter {

    public int x, y;                                                            // Współrzędne x i y myszy.
    public boolean pressed;                                                     // Flaga określająca, czy przycisk myszy jest wciśnięty
    
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
