package main.piece;

import main.GamePanel;

public class King extends Piece {

    public King(int color, int column, int row) {
        super(color, column, row);
        // Ustala obrazek króla na podstawie jego koloru
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_king");
        } else {
            image = getImage("/res/piece/b_king") ;
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        // Sprawdza, czy docelowe pole mieści się na planszy
        if (isWithinBoard(targetCol, targetRow)) {
            // Sprawdza, czy docelowa pozycja jest oddalona o jeden kwadrat od poprzedniej pozycji lub na ukos
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 ||
            Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
        
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}