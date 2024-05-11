package main.piece;

import main.GamePanel;

public class Queen extends Piece{

    public Queen(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/piece/w_queen");
        } else {
            image = getImage("/piece/b_queen") ;
        }
    }
        
}