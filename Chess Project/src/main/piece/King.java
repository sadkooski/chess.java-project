package main.piece;

import main.GamePanel;

public class King extends Piece{

    public King(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/piece/w_king");
        } else {
            image = getImage("/piece/b_king") ;
        }
    }
        
}