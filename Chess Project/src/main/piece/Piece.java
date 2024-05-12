package main.piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;

//// Klasa reprezentująca pojedynczy pionek na szachownicy.
public class Piece {
    
    public BufferedImage image;                                                 // Obrazek reprezentujący pionek
    public int x, y;                                                            // Pozycja pionka na ekranie
    public int column, row, preCol, preRow;                                  // Kolumna i wiersz, w którym znajduje się pionek, oraz jego poprzednia pozycja
    public int color;                                                           // Kolor pionka
    public Piece hittingP;

    /// Kontruktor klasy Piece
    public Piece(int color, int column, int row) {
        
        this.color = color;
        this.column = column;
        this.row = row;
        x = getX(column);                                                       // Obliczenie pozycji x na podstawie kolumny
        y = getY(row);                                                          // Obliczenie pozycji y na podstawie wiersza
        preCol = column;                                                     // Ustawienie poprzedniej kolumny na aktualną kolumnę
        preRow = row;                                                           // Ustawienie poprzedniego wiersza na aktualny wiersz
    }
    
    /// Metoda pobierająca obrazek pionka z pliku o podanej ścieżce.
    public BufferedImage getImage(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));               // Wczytanie obrazka z pliku o podanej ścieżce
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /// Metoda obliczająca pozycję x pionka na podstawie kolumny.
    public int getX(int column) {
        return column * Board.SQUARE_SIZE;                                      // Pozycja x to kolumna pomnożona przez rozmiar kwadratu na planszy
    }

    /// Metoda obliczająca pozycję y pionka na podstawie wiersza.
    public int getY(int row) {
        return row * Board.SQUARE_SIZE;                                         // Pozycja y to wiersz pomnożony przez rozmiar kwadratu na planszy
    }

    /// Metoda obliczająca kolumnę na podstawie pozycji x.
    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    /// Metoda obliczająca wiersz na podstawie pozycji y.
    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getIndex() {
        for(int index = 0; index < GamePanel.simPieces.size(); index ++) {
            if(GamePanel.simPieces.get(index) == this) {
                return index;
            }
        }
        return 0;
    }

    /// Metoda aktualizująca pozycję pionka na planszy.
    public void updatePosition() {
        x = getX(column);                                                       // Aktualizuje pozycję x na podstawie aktualnej kolumny
        y = getY(row);                                                          // Aktualizuje pozycję y na podstawie aktualnego wiersza
        preCol = getCol(x);                                                     // Aktualizuje poprzednią kolumnę na podstawie nowej wartości x
        preRow = getRow(y);                                                     // Aktualizuje poprzedni wiersz na podstawie nowej wartości y
    }

    public void resetPosition() {
        column = preCol;
        row = preRow;
        x = getX(column);
        y = getY(row);
    }

    /// Metoda sprawdzająca możliwość ruchu pionka na planszy.
    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    public boolean isWithinBoard(int targetCol, int targetRow) {
        if(targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }

    public Piece getHittingP(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.simPieces) {
            if(piece.column == targetCol && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {

        hittingP = getHittingP(targetCol, targetRow);

        if(hittingP == null) {                                                  // To pole jest puste
            return true;
        } else {                                                                // To pole jest zajęte
            if(hittingP.color != this.color) {
                return true;
            } else {
                hittingP = null;
            }
        }

        return false;
    }

    /// Metoda rysująca pionek na planszy.
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}