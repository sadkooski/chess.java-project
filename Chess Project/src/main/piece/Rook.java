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
    public boolean canMove(int targetCol, int targetRow) {

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {
        // Wieża może się poruszać tylko po tej samej kolumnie albo wierszu w któym już się znajduje.
            if(targetCol == preCol || targetRow == preRow) {
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    return true;
                }
            }
        }
        return false;
    }
        
}