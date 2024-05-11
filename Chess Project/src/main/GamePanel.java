package main;

import java.awt.Color; //Importuje klasę Color z pakietu java.awt, która pozwala używać kolorów w interfejsie użytkownika.
import java.awt.Dimension; //Importuje klasę Dimension z pakietu java.awt, która jest używana do określania wymiarów obiektów, takich jak rozmiar panelu.
import java.awt.Graphics;
import java.awt.Graphics2D; // Importuje klasę Graphics2D z pakietu java.awt, która dostarcza bardziej zaawansowane funkcje rysowania.
import java.util.ArrayList;

import javax.swing.JPanel; //Importuje klasę JPanel z pakietu javax.swing, która jest kontenerem, który można używać do grupowania innych komponentów GUI.

import main.piece.Bishop;
import main.piece.King;
import main.piece.Knight;
import main.piece.Pawn;
import main.piece.Piece;
import main.piece.Queen;
import main.piece.Rook;

public class GamePanel extends JPanel implements Runnable{  // Rozpoczyna definicję klasy GamePanel, która dziedziczy po klasie JPanel i implementuje interfejs Runnable, co oznacza, że GamePanel będzie działać jak standardowy panel swingowy oraz będzie możliwe uruchomienie go jako wątek.
    
    public static final int WIDTH = 1250; //stała, szerokość panelu
    public static final int HEIGHT = 1000; //stała, wysokość panelu
    final int FPS = 60; // Stała, liczba klatek na sekundę
    Thread gameThread; // Wątek gry
    Board board = new Board(); // Obiekt reprezentujący planszę

    //PIONKI
    public static ArrayList<Piece> pieces = new ArrayList<>(); // Lista przechowująca pionki na planszy w rzeczywistej grze.
    public static ArrayList<Piece> simPieces = new ArrayList<>(); // Lista przechowująca pionki na planszy w symulowanej grze.


    //KOLORY
    public static final int WHITE = 0; // Stała, reprezentująca kolor biały
    public static final int BLACK = 1; // Stała, reprezentująca kolor czarny
    int currentColor = WHITE; // kolor gracza który zaczyna grę

    //Konstruktor klasy GamePanel
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT)); //ustawia rozmiar panelu korzystająć z obiektu 'Dimension'
        setBackground(Color.BLACK); //kolor tła panelu

        setPieces(); // Inicjalizacja pionków na planszy
        copyPieces(pieces, simPieces); // Skopiowanie pionków do symulowanej gry
    }

    // Metoda uruchamiająca grę jako wątek
    public void launchGame() {
        gameThread = new Thread(this); // Tworzy nowy obiekt wątku, który będzie wykonywał metodę run() obiektu, który został przekazany jako parametr.
        gameThread.start(); // Uruchamia nowy wątek, co powoduje rozpoczęcie wykonywania metody run() w nowym wątku. Ta linia kodu powoduje, że run() metoda zaimplementowana w klasie GamePanel zostanie wykonana w osobnym wątku.
    }

    //Tworzenie pionków dla obydwu graczy
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

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        // Kopiowanie listy pionków
        target.clear();
        for(int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }

    // Metoda implementująca działanie wątku
    @Override
    public void run() {
    
        //GAME LOOP
        double drawInterval = 1000000000.0 / FPS; // Określa interwał czasowy między klatkami gry, aby osiągnąć pożądane FPS (klatki na sekundę).
        double delta = 0; // Przechowuje ilość czasu, która upłynęła od ostatniej aktualizacji gry.
        long lastTime = System.nanoTime(); // Pobiera czas systemowy w nanosekundach na początku pętli.
        long currentTime;
    
        while(gameThread != null) { // Pętla będzie działać dopóki wątek gry jest aktywny.
    
            currentTime = System.nanoTime(); // Pobiera aktualny czas systemowy w nanosekundach.
            
            delta += (currentTime - lastTime) / drawInterval; // Oblicza, ile razy należy zaktualizować grę, aby utrzymać pożądane FPS.
            lastTime = currentTime; // Aktualizuje czas ostatniej klatki.
    
            if(delta >= 1) { // Sprawdza, czy należy zaktualizować grę.
                update(); // Aktualizuje stan gry.
                repaint(); // Wywołuje repaint(), aby panel gry został odświeżony.
                delta--; // Zmniejsza wartość delta, aby uwzględnić aktualizację gry.
            }
        }
    }

    //Metoda aktualizująca stan panelu gry.
    private void update() {

    }

    //metoda dziedziczona z klasy JPanel która słuzy do rysowania zawartości panelu.
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Rysuje tło i komponenty panelu

        Graphics2D g2 = (Graphics2D)g; //Zaawansowane rysowanie

        //PLANSZA
        board.draw(g2); // Wywołuje metodę draw z obiektu board, aby narysować planszę na panelu gry

        //PIONKI
        for(Piece p : simPieces) {
            p.draw(g2);
        }
    }
}
