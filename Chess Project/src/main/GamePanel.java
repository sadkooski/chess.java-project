package main;

import java.awt.Color; //Importuje klasę Color z pakietu java.awt, która pozwala używać kolorów w interfejsie użytkownika.
import java.awt.Dimension; //Importuje klasę Dimension z pakietu java.awt, która jest używana do określania wymiarów obiektów, takich jak rozmiar panelu.
import java.awt.Graphics;

import javax.swing.JPanel; //Importuje klasę JPanel z pakietu javax.swing, która jest kontenerem, który można używać do grupowania innych komponentów GUI.

public class GamePanel extends JPanel implements Runnable{  // Rozpoczyna definicję klasy GamePanel, która dziedziczy po klasie JPanel i implementuje interfejs Runnable, co oznacza, że GamePanel będzie działać jak standardowy panel swingowy oraz będzie możliwe uruchomienie go jako wątek.
    
    public static final int WIDTH = 1100; //stała, szerokość panelu
    public static final int HEIGHT = 800; //stała, wysokość panelu
    final int FPS = 60; // Stała, liczba klatek na sekundę
    Thread gameThread; // Wątek gry

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT)); //ustawia rozmiar panelu korzystająć z obiektu 'Dimension'
        setBackground(Color.BLACK); //kolor tła panelu
    }

    // Metoda uruchamiająca grę jako wątek
    public void launchGame() {
        gameThread = new Thread(this); // Tworzy nowy obiekt wątku, który będzie wykonywał metodę run() obiektu, który został przekazany jako parametr.
        gameThread.start(); // Uruchamia nowy wątek, co powoduje rozpoczęcie wykonywania metody run() w nowym wątku. Ta linia kodu powoduje, że run() metoda zaimplementowana w klasie GamePanel zostanie wykonana w osobnym wątku.
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
    }
}
