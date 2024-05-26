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

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {    // Sprawdza, czy docelowe pole mieści się na planszy i nie jest takie samo jak poprzednie pole
    
            /// Definicja ruchu pionka bazując na kolorze gracza (białe tylko do góry, czarne tylko do dołu)
            int moveValue;
            if(color == GamePanel.WHITE) {
                moveValue = -1;                                                                     // Dla białych pionków, ruch jest w kierunku ujemnych wartości rzędów
            } else {
                moveValue = 1;                                                                      // Dla czarnych pionków, ruch jest w kierunku dodatnich wartości rzędów
            }
    
            /// Sprawdzanie zbijanego pionka
            hittingP = getHittingP(targetCol, targetRow);                                           // Sprawdza, czy na docelowym polu znajduje się pionek, który może zostać zbity
    
            /// Ruch o 1 pole
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null) {
                return true;                                                                         // Zwraca true, jeśli ruch jest możliwy o 1 pole do przodu i na docelowym polu nie ma żadnego pionka
            }
    
            /// Ruch o 2 pola
            if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null &&
                    moved == false && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                return true;                                                                        // Zwraca true, jeśli ruch jest możliwy o 2 pola do przodu, na docelowym polu nie ma żadnego pionka, pionek nie był wcześniej przesuwany, a ruch nie jest w linii prostego ruchu
            }
            /// Bicie na ukos
            if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue &&
                    hittingP != null && hittingP.color != color) {
                return true;                                                                        // Zwraca true, jeśli ruch jest możliwy do wykonania na ukos i na docelowym polu znajduje się pionek przeciwnego koloru
            }
        }
        return false;                                                                               // Zwraca false, jeśli ruch nie jest możliwy
    }
    
}
