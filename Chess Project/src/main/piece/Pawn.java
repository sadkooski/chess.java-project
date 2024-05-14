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

    public boolean canMove(int targetCol, int targetRow) {

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {

            /// Definicja ruchu pionka bazując na kolorze gracza (białe tylko do góry, czarne tylko do dołu)
            int moveValue;
            if(color == GamePanel.WHITE) {
                moveValue = -1;
            } else {
                moveValue = 1;
            }

            /// Sprawdzanie zbijanego pionka
            hittingP = getHittingP(targetCol, targetRow);

            /// Ruch o 1 pole
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null) {
                return true;
            }

            /// Ruch o 2 pola
            if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null &&
                    moved == false && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                return true;
            }
            /// Bicie na ukos
            if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue &&
                    hittingP != null && hittingP.color != color) {
                return true;
            }
        }
        return false;
    }
    
}
