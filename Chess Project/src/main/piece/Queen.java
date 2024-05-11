package main.piece;

import main.GamePanel;

public class Queen extends Piece{

    public Queen(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_queen");
        } else {
            image = getImage("/res/piece/b_queen") ;
        }
    }
        
}