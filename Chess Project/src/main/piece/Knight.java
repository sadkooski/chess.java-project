package main.piece;

import main.GamePanel;

public class Knight extends Piece {

    public Knight(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_knight");
        } else {
            image = getImage("/res/piece/b_knight") ;
        }
    }
    public boolean canMove(int targetCol, int targetRow) {

        if(isWithinBoard(targetCol, targetRow)) {
            /// Goniec może się poruszać tylko jeśli jego movement ratio w kolumnnie i wierszu planszy wynosi 1:2 albo 2:1
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}