package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
    
    final int MAX_COLUMNE = 8;                                                  // Maksymalna liczba kolumn na planszy
    final int MAX_ROW = 8;                                                      // Maksymalna liczba wierszy na planszy
    public static final int SQUARE_SIZE = 125;                                  // Stała określająca rozmiar kwadratu na planszy
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2;                 // Stała określająca połowę rozmiaru kwadratu

    public void draw(Graphics2D g2) {

        int color = 0;                                                          // Zmienna kontrolująca kolor kwadratu na planszy
        
        for(int row = 0; row < MAX_ROW; row++) {                                // Iteracja przez wszystkie wiersze na planszy
            for(int col = 0; col < MAX_COLUMNE; col++) {                        // Iteracja przez wszystkie kolumny na planszy

                if(color == 0) {                                                // Wybór koloru kwadratu na planszy
                    g2.setColor(new Color(157, 172, 255));                // Ustawienie koloru nieparzystego kwadratu
                    color = 1;                                                  // Zmiana koloru na kolejnej iteracji
                } else {
                    g2.setColor(new Color(111, 115, 210));                // Ustawienie koloru parzystego kwadratu
                    color = 0;                                                  // Zmiana koloru na kolejnej iteracji
                }
                g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);            // Narysowanie kwadratu na planszy
            }
            if(color == 0) {                                                    // Rozwiązuje problem z kolorami pól we wszsytkich wierszach
                color = 1;
            } else {
                color = 0;
            }
        }
    }
}
