package main.piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;

//// Klasa reprezentująca pojedynczy pionek na szachownicy.
public class Piece {
    
    public BufferedImage image; // Obrazek reprezentujący pionek
    public int x, y; // Pozycja pionka na ekranie
    public int column, row, preColumn, preRow; // Kolumna i wiersz, w którym znajduje się pionek, oraz jego poprzednia pozycja
    public int color; // Kolor pionka

    /// Kontruktor klasy Piece
    public Piece(int color, int column, int row) {
        
        this.color = color;
        this.column = column;
        this.row = row;
        x = getX(column); // Obliczenie pozycji x na podstawie kolumny
        y = getY(row); // Obliczenie pozycji y na podstawie wiersza
        preColumn = column; // Ustawienie poprzedniej kolumny na aktualną kolumnę
        preRow = row; // Ustawienie poprzedniego wiersza na aktualny wiersz
    }
    
    /// Metoda pobierająca obrazek pionka z pliku o podanej ścieżce.
    public BufferedImage getImage(String imagePath) {

        BufferedImage image = null;

        try {
            // Wczytanie obrazka z pliku o podanej ścieżce
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /// Metoda obliczająca pozycję x pionka na podstawie kolumny.
    public int getX(int column) {
        return column * Board.SQUARE_SIZE; // Pozycja x to kolumna pomnożona przez rozmiar kwadratu na planszy
    }

    /// Metoda obliczająca pozycję y pionka na podstawie wiersza.
    public int getY(int row) {
        return row * Board.SQUARE_SIZE; // Pozycja y to wiersz pomnożony przez rozmiar kwadratu na planszy
    }

    /// Metoda obliczająca kolumnę na podstawie pozycji x.
    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    /// Metoda obliczająca wiersz na podstawie pozycji y.
    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    /// Metoda aktualizująca pozycję pionka na planszy.
    public void updatePosition() {
        x = getX(column); // Aktualizuje pozycję x na podstawie aktualnej kolumny
        y = getY(row); // Aktualizuje pozycję y na podstawie aktualnego wiersza
        preColumn = getCol(x); // Aktualizuje poprzednią kolumnę na podstawie nowej wartości x
        preRow = getRow(y); // Aktualizuje poprzedni wiersz na podstawie nowej wartości y
    }

    /// Metoda sprawdzająca możliwość ruchu pionka na planszy.
    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    /// Metoda rysująca pionek na planszy.
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}