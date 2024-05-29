package main;

import java.awt.Color;
import java.awt.Graphics2D;
public class Board {
    
    final int MAX_COLUMNE = 8;
    final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 125;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2;

    public void draw(Graphics2D g2) {

        int color = 0;
        
        for(int row = 0; row < MAX_ROW; row++) {
            for(int col = 0; col < MAX_COLUMNE; col++) {

                if(color == 0) {
                    g2.setColor(new Color(157, 172, 255));
                    color = 1;
                } else {
                    g2.setColor(new Color(111, 115, 210));
                    color = 0;
                }
                g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
            if(color == 0) {
                color = 1;
            } else {
                color = 0;
            }
        }
    }
}
