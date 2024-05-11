package main;

import java.awt.Color; //Importuje klasę Color z pakietu java.awt, która pozwala używać kolorów w interfejsie użytkownika.
import java.awt.Dimension; //Importuje klasę Dimension z pakietu java.awt, która jest używana do określania wymiarów obiektów, takich jak rozmiar panelu.
import java.awt.Graphics;

import javax.swing.JPanel; //Importuje klasę JPanel z pakietu javax.swing, która jest kontenerem, który można używać do grupowania innych komponentów GUI.

public class GamePanel extends JPanel{  //Rozpoczyna definicję klasy GamePanel, która dziedziczy po klasie JPanel, co oznacza, że ​​GamePanel będzie działać jak standardowy panel swingowy, ale można go dostosować do potrzeb gry szachowej.
    
    public static final int WIDTH = 1100; //stała, szerokość panelu
    public static final int HEIGHT = 800; //stała, wysokość panelu

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT)); //ustawia rozmiar panelu korzystająć z obiektu 'Dimension'
        setBackground(Color.BLACK); //kolor tła panelu
    }
    
    //Metoda która aktualizuje stan panelu gry.
    private void update() {

    }
    //metoda dziedziczona z klasy JPanel która słuzy do rysowania zawartości panelu.
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Rysuje tło i komponenty panelu
    }
}
