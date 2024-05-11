package main.piece;

import main.GamePanel;

public class Knight extends Piece{

    public Knight(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_knight");
        } else {
            image = getImage("/res/piece/b_knight") ;
        }
    }
        
}