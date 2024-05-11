package main.piece;

import main.GamePanel;

public class King extends Piece{

    public King(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_king");
        } else {
            image = getImage("/res/piece/b_king") ;
        }
    }
        
}