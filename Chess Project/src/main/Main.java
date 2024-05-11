package main;

import javax.swing.JFrame; // Klasa J.Frame która jest komponentem GUI w Java Swing, który reprezentuje okno aplikacji.
// Metoda main - punkt wejścia programu. Tworzy i konfiguruje główne okno aplikacji
public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Chess Project"); // Tworzenie głównego okna aplikacji
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ustawienie operacji zamknięcia aplikacji po zamknięciu okna
        window.setResizable(false); // Uniemożliwienie zmiany rozmiaru okna przez użytkownika
        
        // Tworzy GamePanel i dodaje do window
        GamePanel gp = new GamePanel(); // Tworzy nowy panel gry.
        window.add(gp); // Dodaje panel gry do głównego okna.
        window.pack(); // Dopasowuje rozmiar okna do rozmiaru panelu gry.

        window.setLocationRelativeTo(null); // Ustawienie położenia okna na środku ekranu
        window.setVisible(true);// Ustawienie widoczności okna

        gp.launchGame(); //Uruchamia grę
    }
}
