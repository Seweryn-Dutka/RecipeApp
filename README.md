# Aplikacja z przepisami

Aplikacja z przepisami umożliwia użytkownikom rejestrację, logowanie oraz obliczanie BMR (Basal Metabolic Rate) na podstawie danych takich jak waga, wzrost, wiek i płeć. Dodatkowo użytkownicy mogą dodawać własne przepisy kulinarne, zarządzać nimi i przeglądać dodane przepisy. 

## Funkcje:
- Rejestracja i logowanie użytkowników.
- Obliczanie BMR na podstawie wprowadzonych danych (waga, wzrost, wiek, płeć).
- Dodawanie, edytowanie i usuwanie przepisów kulinarnych.
- Przeglądanie i wyszukiwanie przepisów.

## Testy Manualne


| ID     | Tytuł                                | Warunki początkowe        | Kroki testowe                                                                 | Oczekiwany rezultat                                 |
|--------|--------------------------------------|---------------------------|-------------------------------------------------------------------------------|----------------------------------------------------|
| TC001  | Rejestracja z poprawnymi danymi      | Strona rejestracji otwarta | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Register”. | Użytkownik zostaje zarejestrowany.                |
| TC002  | Rejestracja z brakującymi danymi     | Strona rejestracji otwarta | 1. Zostaw pole „email” puste. <br> 2. Kliknij „Register”.                    | Komunikat: „Enter email”.                         |
| TC003  | Logowanie z poprawnymi danymi       | Strona logowania otwarta  | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Login”.  | Użytkownik zostaje zalogowany.                    |
| TC004  | Logowanie z błędnymi danymi         | Strona logowania otwarta  | 1. Wprowadź nieprawidłowe hasło. <br> 2. Kliknij „Login”.                   | Komunikat: „Authentication failed”.               |

| TC005  | Obliczanie BMR z poprawnymi danymi   | Strona BMR otwarta        | 1. Wprowadź poprawne dane: płeć, wiek, waga, wzrost. <br> 2. Kliknij „Count BMR”. | Wyświetlenie obliczonego BMR.                      |
| TC006  | Obliczanie BMR z brakującymi danymi  | Strona BMR otwarta        | 1. Zostaw pole waga puste. <br> 2. Kliknij „Count BMR”.                       | Komunikat: „Please fill in all fields”.           |
| TC007  | Obliczanie BMR bez podania danych   | Strona BMR otwarta        | 1. Zostaw wszystkie pola puste. <br> 2. Kliknij „Count BMR”.                 | Komunikat: „Please fill in all fields”.           |
| TC008  | Obliczanie BMR z wprowadzeniem jednego parametru | Strona BMR otwarta | 1. Wprowadź tylko jeden parametr (np. tylko wagę). <br> 2. Kliknij „Count BMR”. | Komunikat: „Please fill in all fields”.           |
| TC009  | Dodanie nowego przepisu              | Strona dodawania przepisu otwarta | 1. Wprowadź nazwę przepisu, składniki, opis i kalorie. <br> 2. Kliknij „Add Recipe”. | Przepis zostaje zapisany i wyświetla się w liście przepisów. |

| TC010  | Dodanie nowego przepisu z brakującymi danymi | Strona dodawania przepisu otwarta | 1. Wprowadź tylko nazwę przepisu i składniki, zostaw pole "opis" puste. <br> 2. Kliknij „Add Recipe”. | Komunikat: „Please fill in all fields”.            |
