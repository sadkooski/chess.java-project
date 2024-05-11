package main.piece;

import main.GamePanel;

public class Pawn extends Piece{

    public Pawn(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/piece/w_pawn");
        } else {
            image = getImage("/piece/b_pawn") ;
        }
    }
        
}
