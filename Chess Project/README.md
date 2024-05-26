# Projekt Szachy w Javie

- Typ projektu: Gra planszowa
- Tytuł projektu: chess.java-project

## Opis

- Projekt ten ma na celu stworzenie funkcjonalnej gry w szachy w języku Java. Zawiera różne klasy dla różnych figur szachowych, planszę do gry oraz logikę gry.

- Oto opis zaimplementowanych funckjonalności:

## Funkcje

- Implementacja ruchów figur szachowych zgodnie z zasadami szachów
- Implementacja pętli gry
- Implementacja planszy do gry
- Implementacja systemu turowego rozgrywki
- Obsługa zdarzeń na kliknięciach myszy
- Graficzny interfejs użytkownika (GUI)

- Main.java
1. Tworzenie głównego okna aplikacji za pomocą klasy JFrame, które reprezentuje okno aplikacji.
2. Konfiguracja działania aplikacji, takiej jak obsługa zamknięcia okna.
3. Uniemożliwienie zmiany rozmiaru okna przez użytkownika.
4. Tworzenie panelu gry (GamePanel) i dodawanie go do głównego okna.
5. Dopasowywanie rozmiaru okna do rozmiaru panelu gry.
6. Ustawienie położenia okna na środku ekranu.
7. Ustawienie widoczności okna.
8. Uruchamianie gry poprzez wywołanie metody launchGame() na panelu gry.

- Mouse.java
1. Tworzenie głównego okna aplikacji za pomocą klasy JFrame, która reprezentuje okno aplikacji.
2. Konfiguracja działania aplikacji, takiej jak obsługa zamknięcia okna.
3. Uniemożliwienie zmiany rozmiaru okna przez użytkownika.
4. Tworzenie panelu gry (GamePanel) i dodawanie go do głównego okna.
5. Dopasowywanie rozmiaru okna do rozmiaru panelu gry.
6. Ustawienie położenia okna na środku ekranu.
7. Ustawienie widoczności okna.
8. Uruchamianie gry poprzez wywołanie metody launchGame() na panelu gry.
9. Implementacja obsługi zdarzeń myszy za pomocą klasy Mouse i jej metod:
    - mousePressed(MouseEvent e) - obsługuje zdarzenie wciśnięcia przycisku myszy.
    - mouseReleased(MouseEvent e) - obsługuje zdarzenie puszczenia przycisku myszy.
    - mouseDragged(MouseEvent e) - obsługuje zdarzenie przeciągania myszy.
    - mouseMoved(MouseEvent e) - obsługuje zdarzenie ruchu myszy.

- GamePanel.java
1. Tworzenie głównego okna aplikacji za pomocą klasy JPanel, która jest kontenerem dla komponentów GUI.
2. Inicjalizacja stałych, takich jak szerokość i wysokość panelu oraz liczba klatek na sekundę (FPS).
3. Utworzenie wątku gry i rozpoczęcie jego działania.
4. Tworzenie i zarządzanie planszą gry oraz obiektem do obsługi zdarzeń myszy.
5. Utworzenie list przechowujących pionki na planszy w rzeczywistej i symulowanej grze.
6. Implementacja metod do ustawiania pionków na planszy dla obydwu graczy oraz kopiowania listy pionków.
7. Implementacja głównej metody run(), która kontroluje główną pętlę gry (game loop).
8. Implementacja metody update(), która aktualizuje stan gry w każdej klatce.
9. Implementacja metod do obsługi ruchu myszy, w tym mousePressed(), mouseReleased(), mouseDragged() i mouseMoved().
10. Symulowanie ruchu aktywnego pionka na podstawie pozycji myszy.
11. Zmiana kolejności ruchu gracza co turę.
12. Rysowanie planszy, pionków i komunikatów na panelu gry za pomocą metod paintComponent() i draw().
13. Wykorzystanie zaawansowanych funkcji graficznych, takich jak przezroczystość, wygładzanie tekstu i renderowanie dwuwymiarowe (Graphics2D).

- Board.java
1. Rysowanie Planszy:
    - Klasa Board odpowiedzialna jest za rysowanie szachownicy w interfejsie użytkownika.
    - Wykorzystuje klasy z pakietu java.awt do rysowania kwadratów reprezentujących pola na planszy.
    - Określa rozmiar oraz kolory poszczególnych pól na szachownicy.
2. Inicjowanie Planszy:
    - Ustala maksymalną liczbę kolumn i wierszy na planszy oraz rozmiar kwadratu reprezentującego pojedyncze pole.
    - Wykorzystuje stałe wartości do określenia rozmiaru kwadratu oraz jego połowy.
3. Iteracja przez Pola:
    - Wykorzystuje pętle iteracyjne do przejścia przez wszystkie pola na planszy.
    - Określa kolor każdego pola na podstawie jego indeksu w szachownicy.
4. Rysowanie Kwadratów:
    - Używa obiektu Graphics2D do rysowania kwadratów na planszy.
    - Wykorzystuje kolor zdefiniowany dla każdego pola do wypełnienia kwadratu odpowiednim kolorem.
5. Zmiana Kolorów Pól:
    - Zapewnia alternujące kolory pól na planszy, aby uzyskać efekt szachownicy.
    - Iteruje przez wszystkie wiersze i kolumny planszy, aby ustawić odpowiednie kolory dla każdego pola.


## Instalacja

Aby uruchomić ten projekt, musisz mieć zainstalowany zestaw narzędzi do programowania w języku Java (JDK) na swoim systemie. Możesz sklonować to repozytorium na swój komputer lokalny i otworzyć je w preferowanym środowisku programistycznym dla języka Java.

```bash
git clone https://github.com/sadkooski/chess.java-project.git