# Testowanie i Jakość Oprogramowania

## Autor 

### Seweryn Dutka

# Aplikacja z przepisami

Aplikacja z przepisami umożliwia użytkownikom rejestrację, logowanie oraz obliczanie BMR (Basal Metabolic Rate) na podstawie danych takich jak waga, wzrost, wiek i płeć. Dodatkowo użytkownicy mogą dodawać własne przepisy kulinarne, zarządzać nimi i przeglądać dodane przepisy. 

## Uruchomienie Projektu:

W katalogu aplikacji Androidowej (np. recipeapplication), aby uruchomić aplikację, wystarczy kliknąć na ikonę aplikacji w Android Studio i uruchomić ją na emulatorze lub rzeczywistym urządzeniu.

## Testy

### Testy

| **Rodzaj testów**  | **Lokalizacja**                      | **Liczba testów** | **Opis** |
|--------------------|--------------------------------------|-------------------|----------|
| **Jednostkowe**     | `src/test/java/com/example/recipeapplication` | 10                | Testy jednostkowe zostały napisane dla poszczególnych komponentów aplikacji Androidowej. Testują one indywidualne metody i funkcje, takie jak obliczenia BMR, logika kalkulacji kalorii, oraz logika obsługi formularzy. Testy te są kluczowe do zapewnienia poprawności działania poszczególnych elementów aplikacji w izolacji. |
| **Integracyjne**    | `src/test/java/com/example/recipeapplication` | 11                 | Testy integracyjne obejmują połączenia między różnymi komponentami aplikacji, takimi jak interakcje z bazą danych , sprawdzanie poprawności działania aktywności oraz integrację z interfejsem użytkownika. Sprawdzają one, jak różne części aplikacji współdziałają ze sobą w kontekście rzeczywistego działania aplikacji. |
| **Manualne**        | Interfejs użytkownika                | 10                | Testy manualne obejmują ręczne testowanie formularzy w aplikacji, takich jak dodawanie przepisów, rejestracja użytkowników, oraz obliczenia BMR. Testy te pomagają upewnić się, że użytkownik może prawidłowo wprowadzać dane, a aplikacja poprawnie reaguje na te dane, oferując odpowiednie komunikaty i weryfikację błędów. |



## Przypadki testowania dla testera maualnego


| ID     | Tytuł                                | Warunki początkowe        | Kroki testowe                                                                 | Oczekiwany rezultat                                 |
|--------|--------------------------------------|---------------------------|-------------------------------------------------------------------------------|----------------------------------------------------|
| TC001  | Rejestracja z poprawnymi danymi      | Strona rejestracji otwarta | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Register”. | Użytkownik zostaje zarejestrowany.                |
| TC002  | Rejestracja z brakującymi danymi     | Strona rejestracji otwarta | 1. Zostaw pole „email” puste. <br> 2. Kliknij „Register”.                    | Komunikat: „Enter email”.                         |
| TC003  | Logowanie z poprawnymi danymi       | Strona logowania otwarta  | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Login”.  | Użytkownik zostaje zalogowany.                    |
| TC004  | Logowanie z błędnymi danymi         | Strona logowania otwarta  | 1. Wprowadź nieprawidłowe hasło. <br> 2. Kliknij „Login”.                   | Komunikat: „Authentication failed”.                |
| TC005  |  Obliczanie BMR z poprawnymi danymi   | Strona BMR otwarta        | 1. Wprowadź poprawne dane: płeć, wiek, waga, wzrost. <br> 2. Kliknij „Count BMR”. | Wyświetlenie obliczonego BMR.                         |
| TC006  | Obliczanie BMR z brakującymi danymi  | Strona BMR otwarta        | 1. Zostaw pole waga puste. <br> 2. Kliknij „Count BMR”.                       | Komunikat: „Please fill in all fields”.                     |
| TC007  | Obliczanie BMR bez podania danych   | Strona BMR otwarta        | 1. Zostaw wszystkie pola puste. <br> 2. Kliknij „Count BMR”.                 | Komunikat: „Please fill in all fields”.                 |
| TC008  | Obliczanie BMR z wprowadzeniem jednego parametru | Strona BMR otwarta | 1. Wprowadź tylko jeden parametr (np. tylko wagę). <br> 2. Kliknij „Count BMR”. | Komunikat: „Please fill in all fields”.                         |
| TC009  | Dodanie nowego przepisu              | Strona dodawania przepisu otwarta | 1. Wprowadź nazwę przepisu, składniki, opis i kalorie. <br> 2. Kliknij „Add Recipe”. | Przepis zostaje zapisany i wyświetla się w liście przepisów.                   |
| TC010  | Dodanie nowego przepisu z brakującymi danymi | Strona dodawania przepisu otwarta | 1. Wprowadź tylko nazwę przepisu i składniki, zostaw pole "opis" puste. <br> 2. Kliknij „Add Recipe”. | Komunikat: „Please fill in all fields”.                



### Technologie użyte w Projekcie

**Firebase**

**Java**

**Android SDK**

**JUnit i Mockito**

**Robolectric**

**SQLite**
