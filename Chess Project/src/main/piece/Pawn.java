package main.piece;

import main.GamePanel;

public class Pawn extends Piece{

    public Pawn(int color, int column, int row) {
        super(color, column, row);

        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_pawn");
        } else {
            image = getImage("/res/piece/b_pawn") ;
        }
    }
        
}
