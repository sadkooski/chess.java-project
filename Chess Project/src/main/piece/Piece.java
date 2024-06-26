package main.piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;

//// Klasa reprezentująca pojedynczy pionek na szachownicy.
public class Piece {
    
    public BufferedImage image;
    public int x, y;
    public int column, row, preCol, preRow;
    public int color;
    public Piece hittingP;
    public boolean moved;

    /// Kontruktor klasy Piece
    public Piece(int color, int column, int row) {
        
        this.color = color;
        this.column = column;
        this.row = row;
        x = getX(column);
        y = getY(row);
        preCol = column;
        preRow = row;
    }
    
    /// Metoda pobierająca obrazek pionka z pliku o podanej ścieżce.
    public BufferedImage getImage(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /// Metoda obliczająca pozycję x pionka na podstawie kolumny.
    public int getX(int column) {
        return column * Board.SQUARE_SIZE;
    }

    /// Metoda obliczająca pozycję y pionka na podstawie wiersza.
    public int getY(int row) {
        return row * Board.SQUARE_SIZE;
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
        x = getX(column);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }

    public void resetPosition() {
        column = preCol;
        row = preRow;
        x = getX(column);
        y = getY(row);
    }

    /// Metoda sprawdzająca możliwość ruchu pionka na planszy.
    public boolean canMove(int targetCol, int targetRow) { return false; }

    public boolean isWithinBoard(int targetCol, int targetRow) {
        if(targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }
    
    public boolean isSameSquare(int targetCol, int targetRow) {
        if(targetCol == preCol && targetRow == preRow) {
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
    
        if(hittingP == null) {
            return true;
        } else {
            if(hittingP.color != this.color) {
                return true;
            } else {
                hittingP = null;
            }
        }
    
        return false;
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