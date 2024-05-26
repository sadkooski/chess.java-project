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
    public int column, row, preCol, preRow;                                     // Kolumna i wiersz, w którym znajduje się pionek, oraz jego poprzednia pozycja
    public int color;                                                           // Kolor pionka
    public Piece hittingP;                                                      // Przechowuje informację o pionku, który może zostać zbity w wyniku wykonania ruchu.
    public boolean moved;                                                       // Określa, czy pionek był już wcześniej przesuwany na planszy.

    /// Kontruktor klasy Piece
    public Piece(int color, int column, int row) {
        
        this.color = color;                                                     // Przypisuje kolor pionka.
        this.column = column;                                                   // Przypisuje kolumnę, w której znajduje się pionek.
        this.row = row;                                                         // Przypisuje rząd, w którym znajduje się pionek.
        x = getX(column);                                                       // Obliczenie pozycji x na podstawie kolumny
        y = getY(row);                                                          // Obliczenie pozycji y na podstawie wiersza
        preCol = column;                                                        // Ustawienie poprzedniej kolumny na aktualną kolumnę
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
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;                  // Oblicza kolumnę na podstawie pozycji x.
    }
    
    /// Metoda obliczająca wiersz na podstawie pozycji y.
    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;                  // Oblicza rząd na podstawie pozycji y.
    }
    
    public int getIndex() {
        for(int index = 0; index < GamePanel.simPieces.size(); index ++) {      // Iteruje przez listę pionków w symulowanej grze.
            if(GamePanel.simPieces.get(index) == this) {                        // Sprawdza, czy bieżący pionek jest równy pionkowi w liście.
                return index;                                                   // Zwraca indeks bieżącego pionka w liście.
            }
        }
        return 0;                                                               // Zwraca 0, jeśli pionek nie został znaleziony w liście.
    }

    /// Metoda aktualizująca pozycję pionka na planszy.
    public void updatePosition() {
        x = getX(column);                                                       // Aktualizuje pozycję x na podstawie aktualnej kolumny
        y = getY(row);                                                          // Aktualizuje pozycję y na podstawie aktualnego wiersza
        preCol = getCol(x);                                                     // Aktualizuje poprzednią kolumnę na podstawie nowej wartości x
        preRow = getRow(y);                                                     // Aktualizuje poprzedni wiersz na podstawie nowej wartości y
        moved = true;
    }

    public void resetPosition() {
        column = preCol;                                                        // Przywraca kolumnę do poprzedniej wartości.
        row = preRow;                                                           // Przywraca rząd do poprzedniej wartości.
        x = getX(column);                                                       // Ustawia pozycję x na podstawie kolumny.
        y = getY(row);                                                          // Ustawia pozycję y na podstawie rzędu.
    }

    /// Metoda sprawdzająca możliwość ruchu pionka na planszy.
    public boolean canMove(int targetCol, int targetRow) { return false; }                          // Zwraca false, gdy ruch pionka nie jest możliwy.

    public boolean isWithinBoard(int targetCol, int targetRow) {
        if(targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;                                                                            // Zwraca true, gdy docelowe pole znajduje się w granicach planszy.
        }
        return false;                                                                               // Zwraca false, gdy docelowe pole znajduje się poza granicami planszy.
    }
    
    public boolean isSameSquare(int targetCol, int targetRow) {
        if(targetCol == preCol && targetRow == preRow) {
            return true;                                                                            // Zwraca true, gdy docelowe pole jest takie samo jak poprzednie pole.
        }
        return false;                                                                               // Zwraca false, gdy docelowe pole różni się od poprzedniego pola.
    }
    
    public Piece getHittingP(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.simPieces) {
            if(piece.column == targetCol && piece.row == targetRow && piece != this) {
                return piece;                                                                       // Zwraca pionek, który zostanie zbity na docelowym polu.
            }
        }
        return null;                                                                                // Zwraca null, gdy na docelowym polu nie ma pionka do zbicia.
    }
    
    public boolean isValidSquare(int targetCol, int targetRow) {
    
        hittingP = getHittingP(targetCol, targetRow);
    
        if(hittingP == null) {                                                  // To pole jest puste
            return true;                                                        // Zwraca true, gdy docelowe pole jest puste.
        } else {                                                                // To pole jest zajęte
            if(hittingP.color != this.color) {
                return true;                                                    // Zwraca true, gdy pionek na docelowym polu może być zbity.
            } else {
                hittingP = null;
            }
        }
    
        return false;                                                           // Zwraca false, gdy ruch na docelowe pole jest niemożliwy.
    }

    public boolean pieceIsOnStraightLine(int targetCol, int targetRow) {
        
        /// Pionek poorusza się w lewo
        for(int c = preCol-1; c > targetCol; c--) {
            for(Piece piece : GamePanel.simPieces) {
                if(piece.column == c && piece.row == targetRow) {
                    hittingP = piece;
                    return true;
                }
            }
        }

        /// Pionek poorusza się w prawo
        for(int c = preCol+1; c < targetCol; c++) {
            for(Piece piece : GamePanel.simPieces) {
                if(piece.column == c && piece.row == targetRow) {
                    hittingP = piece;
                    return true;
                }
            }
        }

        /// Pionek poorusza się do góry
        for(int r = preRow-1; r > targetRow; r--) {
            for(Piece piece : GamePanel.simPieces) {
                if(piece.column == targetCol && piece.row == r) {
                    hittingP = piece;
                    return true;
                }
            }
        }

        /// Pionek poorusza się do dołu
        for(int r = preRow+1; r < targetRow; r++) {
            for(Piece piece : GamePanel.simPieces) {
                if(piece.column == targetCol && piece.row == r) {
                    hittingP = piece;
                    return true;
                }
            }
        }
        /// Nie ma żadnego pionka na drodze
        return false;
    }

    public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow) {

        if(targetRow < preRow) {
        /// Górny lewy skos
            for(int c = preCol-1; c > targetCol; c-- ) {
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces) {
                    if(piece.column == c && piece.row == preRow - diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        /// Górny prawy skos
            for(int c = preCol+1; c < targetCol; c++ ) {
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces) {
                    if(piece.column == c && piece.row == preRow - diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        
        if(targetRow > preRow) {
        /// Dolny lewy skos
            for(int c = preCol-1; c > targetCol; c-- ) {
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces) {
                    if(piece.column == c && piece.row == preRow + diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        /// Dolny lewy skos
            for(int c = preCol+1; c < targetCol; c++ ) {
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces) {
                    if(piece.column == c && piece.row == preRow + diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /// Metoda rysująca pionek na planszy.
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}