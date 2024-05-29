package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.piece.Bishop;
import main.piece.King;
import main.piece.Knight;
import main.piece.Pawn;
import main.piece.Piece;
import main.piece.Queen;
import main.piece.Rook;

//// Rozpoczyna definicję klasy GamePanel, która dziedziczy po klasie JPanel i implementuje interfejs Runnable, co oznacza,
//// że GamePanel będzie działać jak standardowy panel swingowy oraz będzie możliwe uruchomienie go jako wątek.
public class GamePanel extends JPanel implements Runnable{
    
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 1000;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    /// PIONKI
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece activeP;

    /// KOLORY
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    /// BOOLEANS
    boolean canMove;
    boolean validSquare;

    /// Konstruktor klasy GamePanel
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    /// Metoda uruchamiająca grę jako wątek
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /// Tworzenie pionków dla obydwu graczy
    public void setPieces() {

        //Białe pionki
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Rook(WHITE, 7, 7));
        pieces.add(new Knight(WHITE, 1, 7));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Bishop(WHITE, 2, 7));
        pieces.add(new Bishop(WHITE, 5, 7));
        pieces.add(new Queen(WHITE, 3, 7));
        pieces.add(new King(WHITE, 4, 7));

        //Czarne pionki
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Rook(BLACK, 7, 0));
        pieces.add(new Knight(BLACK, 1, 0));
        pieces.add(new Knight(BLACK, 6, 0));
        pieces.add(new Bishop(BLACK, 2, 0));
        pieces.add(new Bishop(BLACK, 5, 0));
        pieces.add(new Queen(BLACK, 3, 0));
        pieces.add(new King(BLACK, 4, 0));
    }
        
    /// Kopiowanie listy pionków
    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        
        for(int i = 0; i < source.size(); i++) {
        target.add(source.get(i));
        }
    }

    /// Metoda implementująca działanie wątku
    @Override
    public void run() {
    
        /// GAME LOOP
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
    
        while(gameThread != null) {
    
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
    
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /// Metoda aktualizująca stan panelu gry.
    private void update() {

       //// Przycisk myszy wciśnięty ////
        if (mouse.pressed) {
            if (activeP == null) {
                for (Piece piece : simPieces) {
                    if (piece.color == currentColor &&
                        piece.column == mouse.x / Board.SQUARE_SIZE &&
                        piece.row == mouse.y / Board.SQUARE_SIZE) {

                    activeP = piece;
                }
            }
        } else {
            simulate();
    }
}

        //// Przycisk myszy puszczony ////
        if (mouse.pressed == false) {

            if (activeP != null) {
                
                if(validSquare) {

                    ///Ruch Potwierdzony

                    ///Zaktualizuj listę pionków w sytuacji gdy zostanie zajęty i usunięty w trakcie symulacji
                    copyPieces(simPieces, pieces);
                    activeP.updatePosition();

                    changePlayer();
                } else {
                    /// Ruch nie jest możliwy, zresetuj wszystko
                    copyPieces(pieces, simPieces);
                    activeP.resetPosition();
                    activeP = null;
                }
            }
        }
    }
    
     /// Metoda symulująca ruch aktywnego pionka na podstawie pozycji myszy.
    private void simulate() {

        canMove = false;
        validSquare = false;

        /// Resetuj listę pionków w każdej pętli
        copyPieces(pieces, simPieces);

        /// Kiedy pionek jest trzymany, zaktualizuj jego pozycję
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activeP.column = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        /// Sprawdza czy pionek jest nad dostępnym polem
        if(activeP.canMove(activeP.column, activeP.row)) {

            canMove = true;

            /// Zbijanie wrogiego pionka
            if(activeP.hittingP != null) {
                simPieces.remove(activeP.hittingP.getIndex());
            }
    
            validSquare = true;
        }
    }
     /// Metoda zmieniająca kolejność ruchu gracza co turę
    private void changePlayer() {
        if(currentColor == WHITE) {
            currentColor = BLACK;
        } else {
            currentColor = WHITE;
        }
        activeP = null;
    }

    /// Metoda dziedziczona z klasy JPanel która służy do rysowania zawartości panelu.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        /// PLANSZA
        board.draw(g2);

        /// PIONKI
        for(Piece p : simPieces) {
            p.draw(g2);
        }

        ///  RYSOWANIE AKTYWNEGO PIONKA
        if(activeP != null) {
            if(canMove) {
                g2.setColor(Color.WHITE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activeP.column*Board.SQUARE_SIZE, activeP.row*Board.SQUARE_SIZE,
                    Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
        activeP.draw(g2);
        }

        /// Pop Up'y o statusie gry
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Book Antiqua", Font.PLAIN, 40));
        g2.setColor(Color.white);
        
        if(currentColor == WHITE) {
            g2.drawString("Ruch białego", 1015, 750);
        } else {
            g2.drawString("Ruch czarnego", 1015, 250);
        }
    }
}
