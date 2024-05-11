package main.piece;

import main.GamePanel;

public class Bishop extends Piece{

    public Bishop(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_bishop");
        } else {
            image = getImage("/res/piece/b_bishop") ;
        }
    }
        
}