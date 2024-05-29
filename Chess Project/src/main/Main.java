package main;

import javax.swing.JFrame;
//// Metoda main - punkt wejścia programu. Tworzy i konfiguruje główne okno aplikacji
public class Main {
    public static void main(String[] args) {

        /// Tworzenie głównego okna aplikacji
        JFrame window = new JFrame("Chess Project");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        /// Tworzy GamePanel i dodaje do window
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.launchGame();
    }
}
