# Projekt Szachy w Javie

- Typ projektu: Gra planszowa
- Tytuł projektu: chess.java-project

## Opis

- Projekt ten ma na celu stworzenie funkcjonalnej gry w szachy w języku Java. Zawiera różne klasy dla różnych figur szachowych, planszę do gry oraz logikę gry wraz z szatą graficzną.

## Funkcje

- Implementacja ruchów figur szachowych zgodnie z zasadami szachów
- Implementacja pętli gry
- Implementacja planszy do gry
- Implementacja systemu turowego rozgrywki
- Obsługa zdarzeń na kliknięciach myszy
- Graficzny interfejs użytkownika (GUI)

- Main.java
- Definiuje klasę Main.
- Zawiera metodę main, która jest punktem wejścia programu.
- Tworzy główne okno aplikacji typu JFrame.
- Konfiguruje główne okno aplikacji, ustawiając tytuł, obsługę zamknięcia okna oraz niemożliwość zmiany rozmiaru.
- Tworzy obiekt klasy GamePanel (panel gry) i dodaje go do głównego okna aplikacji.
- Pakuje elementy w oknie, aby dopasować je do preferowanych rozmiarów.
- Ustawia położenie głównego okna aplikacji na środku ekranu.
- Ustawia widoczność głównego okna aplikacji.
- Uruchamia grę poprzez wywołanie metody launchGame() na obiekcie GamePanel.

- Mouse.java
- Definiuje klasę Mouse, która rozszerza klasę MouseAdapter i obsługuje zdarzenia związane z myszą.
- Zawiera zmienne x i y, które przechowują aktualne współrzędne myszy.
- Zawiera zmienną logiczną pressed, która określa, czy przycisk myszy jest aktualnie wciśnięty.
- Metoda mousePressed(MouseEvent e) obsługuje zdarzenie wciśnięcia przycisku myszy i ustawia zmienną pressed na true.
- Metoda mouseReleased(MouseEvent e) obsługuje zdarzenie puszczenia przycisku myszy i ustawia zmienną pressed na false.
- Metoda mouseDragged(MouseEvent e) obsługuje zdarzenie przeciągania myszy i aktualizuje zmienne x i y na podstawie aktualnej pozycji myszy.
- Metoda mouseMoved(MouseEvent e) obsługuje zdarzenie ruchu myszy i aktualizuje zmienne x i y na podstawie aktualnej pozycji myszy.

- GamePanel.java
- Definiuje klasę GamePanel, która dziedziczy po klasie JPanel i implementuje interfejs Runnable, co oznacza, że może działać jako standardowy panel swingowy oraz być uruchamiana jako wątek.
- Zawiera stałe WIDTH i HEIGHT, określające szerokość i wysokość panelu gry.
- Zawiera zmienną FPS, określającą liczbę klatek na sekundę w grze.
- Deklaruje wątek gameThread oraz obiekty board i mouse.
- Zawiera listy pieces i simPieces, które przechowują pionki na planszy oraz ich kopię używaną do symulacji ruchów.
- Deklaruje zmienną activeP, która przechowuje aktywny pionek.
- Zawiera stałe WHITE i BLACK, które reprezentują kolor graczy.
- Zawiera zmienne logiczne canMove i validSquare, które określają możliwość ruchu pionka i poprawność wybranego pola.
- W konstruktorze GamePanel ustawia preferowany rozmiar panelu, tło na czarny, dodaje nasłuchiwacze zdarzeń myszy oraz inicjuje pionki na planszy.
- Metoda launchGame() uruchamia grę jako wątek.
- Metoda setPieces() inicjuje pionki dla obu graczy na planszy.
- Metoda copyPieces() kopiuje listę pionków z jednej listy do drugiej.
- Metoda run() implementuje główną pętlę gry (game loop), odpowiedzialną za aktualizację i renderowanie.
- Metoda update() aktualizuje stan panelu gry na podstawie ruchów myszy.
- Metoda simulate() symuluje ruch aktywnego pionka na podstawie pozycji myszy.
- Metoda changePlayer() zmienia aktualnego gracza po wykonaniu ruchu.
- Metoda paintComponent(Graphics g) rysuje zawartość panelu gry, włącznie z planszą, pionkami, aktywnym pionkiem oraz informacjami o stanie gry (np. ruchu aktualnego gracza).

- Board.java
- Definiuje klasę Board, która reprezentuje szachownicę.
- Zawiera stałe MAX_COLUMN i MAX_ROW ustawione na 8, określające maksymalną liczbę kolumn i wierszy na szachownicy.
- Zawiera stałe SQUARE_SIZE i HALF_SQUARE_SIZE, które określają rozmiar kwadratu reprezentującego pojedyncze pole na szachownicy oraz jego połowę.
- Zawiera metodę draw(Graphics2D g2), która rysuje szachownicę na podanej grafice 2D.
- W metodzie draw, iteruje przez wszystkie pola na szachownicy i wypełnia je odpowiednim kolorem, aby uzyskać efekt szachownicy.
- Kolory pól są ustawiane na zmiennej color, która zmienia się między białym i czarnym kolorem, aby uzyskać charakterystyczne szachownicowe wzory.
- Na podstawie wartości zmiennej color ustawiany jest odpowiedni kolor (jasny lub ciemny).
- Kwadraty są rysowane w odpowiednich kolumnach i wierszach, korzystając z wartości stałych SQUARE_SIZE i HALF_SQUARE_SIZE.


## Instalacja

Aby uruchomić ten projekt, musisz mieć zainstalowany zestaw narzędzi do programowania w języku Java (JDK) na swoim systemie. Możesz sklonować to repozytorium na swój komputer lokalny i otworzyć je w preferowanym środowisku programistycznym dla języka Java.

```bash
git clone https://github.com/sadkooski/chess.java-project.git
