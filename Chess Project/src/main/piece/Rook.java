package main.piece;

import main.GamePanel;

public class Rook extends Piece{

    public Rook(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_rook");
        } else {
            image = getImage("/res/piece/b_rook") ;
        }
    }
        
}