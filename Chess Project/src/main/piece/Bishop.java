package main.piece;

import main.GamePanel;
/// Klasa reprezentująca figurę "Goniec", dziedziczy po klasie Piece
public class Bishop extends Piece{

    /// Konstruktor klasy Bishop
    public Bishop(int color, int column, int row) {
        super(color, column, row);
        
        if(color == GamePanel.WHITE) {
            image = getImage("/res/piece/w_bishop");
        } else {
            image = getImage("/res/piece/b_bishop");
        }
    }

    /// Metoda sprawdzająca możliwość ruchu dla gonca
    public boolean canMove(int targetCol, int targetRow) {
    
        if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {

            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false) {

                    return true;
                }
            }
        }
        return false;
    }
}