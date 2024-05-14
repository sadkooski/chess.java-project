package main;

import java.awt.AlphaComposite;
import java.awt.Color;                                                          //Importuje klasę Color z pakietu java.awt, która pozwala używać kolorów w interfejsie użytkownika.
import java.awt.Dimension;                                                      //Importuje klasę Dimension z pakietu java.awt, która jest używana do określania wymiarów obiektów, takich jak rozmiar panelu.
import java.awt.Graphics;
import java.awt.Graphics2D;                                                     // Importuje klasę Graphics2D z pakietu java.awt, która dostarcza bardziej zaawansowane funkcje rysowania.
import java.util.ArrayList;

import javax.swing.JPanel;                                                      //Importuje klasę JPanel z pakietu javax.swing, która jest kontenerem, który można używać do grupowania innych komponentów GUI.

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
    
    public static final int WIDTH = 1250;                                       //stała, szerokość panelu
    public static final int HEIGHT = 1000;                                      //stała, wysokość panelu
    final int FPS = 60;                                                         // Stała, liczba klatek na sekundę
    Thread gameThread;                                                          // Wątek gry
    Board board = new Board();                                                  // Obiekt reprezentujący planszę
    Mouse mouse = new Mouse();                                                  // Obiekt do obsługi zdarzeń z myszką

    /// PIONKI
    public static ArrayList<Piece> pieces = new ArrayList<>();                  // Lista przechowująca pionki na planszy w rzeczywistej grze.
    public static ArrayList<Piece> simPieces = new ArrayList<>();               // Lista przechowująca pionki na planszy w symulowanej grze.
    Piece activeP;

    /// KOLORY
    public static final int WHITE = 0;                                          // Stała, reprezentująca kolor biały
    public static final int BLACK = 1;                                          // Stała, reprezentująca kolor czarny
    int currentColor = WHITE;                                                   // kolor gracza który zaczyna grę

    /// BOOLEANS
    boolean canMove;
    boolean validSquare;

    /// Konstruktor klasy GamePanel
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));                          //ustawia rozmiar panelu korzystająć z obiektu 'Dimension'
        setBackground(Color.BLACK);                                             //kolor tła panelu
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();                                                            // Inicjalizacja pionków na planszy
        copyPieces(pieces, simPieces);                                          // Skopiowanie pionków do symulowanej gry
    }

    /// Metoda uruchamiająca grę jako wątek
    public void launchGame() {
        gameThread = new Thread(this);                                          // Tworzy nowy obiekt wątku, który będzie wykonywał metodę run() obiektu, który został przekazany jako parametr.
        gameThread.start();                                                     // Uruchamia nowy wątek, co powoduje rozpoczęcie wykonywania metody run() w nowym wątku. Ta linia kodu powoduje, że run() metoda zaimplementowana w klasie GamePanel zostanie wykonana w osobnym wątku.
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
        pieces.add(new Rook(WHITE, 4, 7));
        pieces.add(new Knight(WHITE, 1, 7));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Bishop(WHITE, 2, 4));
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
        target.clear();                                                         // Czyszczenie listy docelowej przed kopiowaniem
        
        for(int i = 0; i < source.size(); i++) {                                // Kopiowanie pionków z listy źródłowej do listy docelowej
        target.add(source.get(i));
        }
    }

    /// Metoda implementująca działanie wątku
    @Override
    public void run() {
    
        /// GAME LOOP
        double drawInterval = 1000000000.0 / FPS;                               // Określa interwał czasowy między klatkami gry, aby osiągnąć pożądane FPS (klatki na sekundę).
        double delta = 0;                                                       // Przechowuje ilość czasu, która upłynęła od ostatniej aktualizacji gry.
        long lastTime = System.nanoTime();                                      // Pobiera czas systemowy w nanosekundach na początku pętli.
        long currentTime;
    
        while(gameThread != null) {                                             // Pętla będzie działać dopóki wątek gry jest aktywny.
    
            currentTime = System.nanoTime();                                    // Pobiera aktualny czas systemowy w nanosekundach.
            
            delta += (currentTime - lastTime) / drawInterval;                   // Oblicza, ile razy należy zaktualizować grę, aby utrzymać pożądane FPS.
            lastTime = currentTime;                                             // Aktualizuje czas ostatniej klatki.
    
            if(delta >= 1) {                                                    // Sprawdza, czy należy zaktualizować grę.
                update();                                                       // Aktualizuje stan gry.
                repaint();                                                      // Wywołuje repaint(), aby panel gry został odświeżony.
                delta--;                                                        // Zmniejsza wartość delta, aby uwzględnić aktualizację gry.
            }
        }
    }

    /// Metoda aktualizująca stan panelu gry.
    private void update() {

       //// Przycisk myszy wciśnięty ////
        if (mouse.pressed) {                                                    // Sprawdza, czy przycisk myszy został wciśnięty.
            if (activeP == null) {                                              // Sprawdza, czy aktywny pionek nie został jeszcze wybrany.
                for (Piece piece : simPieces) {                                 // Iteruje przez pionki w symulowanej grze.
                    if (piece.color == currentColor &&                          // Sprawdza, czy kolor pionka zgadza się z kolorem aktualnego gracza.
                        piece.column == mouse.x / Board.SQUARE_SIZE &&          // Sprawdza, czy kolumna pionka jest równa kolumnie myszy.
                        piece.row == mouse.y / Board.SQUARE_SIZE) {             // Sprawdza, czy rząd pionka jest równy rzędowi myszy.

                    activeP = piece;                                            // Ustawia aktywnego pionka na wybrany pionek.
                }
            }
        } else {                                                                // Jeśli aktywny pionek już istnieje, symuluj ruch.
            simulate();
    }
}

        //// Przycisk myszy puszczony ////
        if (mouse.pressed == false) {                                                   // Sprawdza, czy przycisk myszy został puszczony.

            if (activeP != null) {                                              // Sprawdza, czy aktywny pionek istnieje.
                
                if(validSquare) {

                    ///Ruch Potwierdzony

                    ///Zaktualizuj listę pionków w sytuacji gdy zostanie zajęty i usunięty w trakcie symulacji
                    copyPieces(simPieces, pieces);
                    activeP.updatePosition();                                   // Aktualizuje pozycję aktywnego pionka.
                } else {
                    /// Ruch nie jest możliwy, zresetuj wszystko
                    copyPieces(pieces, simPieces);
                    activeP.resetPosition();
                    activeP = null;                                             // Resetuje aktywnego pionka.
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
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;                           // Ustawia pozycję x aktywnego pionka na pozycję myszy z odjęciem połowy rozmiaru kwadratu planszy.
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;                           // Ustawia pozycję y aktywnego pionka na pozycję myszy z odjęciem połowy rozmiaru kwadratu planszy.
        activeP.column = activeP.getCol(activeP.x);                             // Oblicza nową kolumnę aktywnego pionka na podstawie jego nowej pozycji x.
        activeP.row = activeP.getRow(activeP.y);                                // Oblicza nowy rząd aktywnego pionka na podstawie jego nowej pozycji y.

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

    /// Metoda dziedziczona z klasy JPanel która służy do rysowania zawartości panelu.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                                                // Rysuje tło i komponenty panelu

        Graphics2D g2 = (Graphics2D)g;                                          //Zaawansowane rysowanie

        /// PLANSZA
        board.draw(g2);                                                         // Wywołuje metodę draw z obiektu board, aby narysować planszę na panelu gry

        /// PIONKI
        for(Piece p : simPieces) {                                              // Iteruje przez wszystkie pionki w symulowanej grze.
            p.draw(g2);                                                         // Wywołuje metodę draw każdego pionka, aby go narysować na panelu gry.
        }

        ///  RYSOWANIE AKTYWNEGO PIONKA
        if(activeP != null) {
            if(canMove) {                                                                           // Sprawdza, czy istnieje aktywny pionek.
                g2.setColor(Color.WHITE);                                                           // Ustawia kolor na biały.
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));   // Ustawia kompozyt alfa na 0.7 (przezroczystość).
                g2.fillRect(activeP.column*Board.SQUARE_SIZE, activeP.row*Board.SQUARE_SIZE,
                    Board.SQUARE_SIZE, Board.SQUARE_SIZE);                                              // Rysuje prostokąt pod aktywnym pionkiem.
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));     // Resetuje kompozyt alfa.
            }
        activeP.draw(g2);                                                                           // Rysuje aktywny pionek.
        }
    }
}
