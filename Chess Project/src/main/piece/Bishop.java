package main.piece;

import main.GamePanel;
/// Klasa reprezentująca figurę "Goniec", dziedziczy po klasie Piece
public class Bishop extends Piece{

    /// Konstruktor klasy Bishop
    public Bishop(int color, int column, int row) {
        super(color, column, row);                                              // Wywołuje konstruktor klasy bazowej Piece
        
        if(color == GamePanel.WHITE) {                                          // Sprawdza kolor pionka i ustawia odpowiedni obrazek
            image = getImage("/res/piece/w_bishop");                  // Obrazek dla białego pionka
        } else {
            image = getImage("/res/piece/b_bishop");                  // Obrazek dla czarnego pionka
        }
    }

    /// Metoda sprawdzająca możliwość ruchu dla gonca
    public boolean canMove(int targetCol, int targetRow) {
    
        if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {                       // Sprawdza, czy docelowa pozycja mieści się na planszy i czy nie jest tą samą pozycją

            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {                                          // Sprawdza, czy docelowa pozycja jest na przekątnej od aktualnej pozycji
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false) {       // Sprawdza, czy docelowa pozycja jest poprawna i czy na przekątnej nie ma innej figury

                    return true;                                                // Zwraca true, jeśli ruch jest możliwy
                }
            }
        }
        return false;                                                           // Zwraca false, jeśli ruch nie jest możliwy
    }
}