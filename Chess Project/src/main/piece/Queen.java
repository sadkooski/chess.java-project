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

    public boolean canMove(int targetCol, int targetRow) {

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {
    
            /// Ruch pionowy i poziomy
            if(targetCol == preCol || targetRow == preRow) {
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    return true;                                                // Zwraca true, gdy ruch jest możliwy w pionie lub poziomie i nie ma przeszkód.
                }
            }
    
            /// Ruch ukośny
            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false) {
                    return true;                                                // Zwraca true, gdy ruch jest możliwy na ukos i nie ma przeszkód.
                }
            }
        }
        return false;                                                           // Zwraca false, gdy ruch na docelowe pole jest niemożliwy.
    }
        
}