# Aplikacja z przepisami

Aplikacja z przepisami umożliwia użytkownikom rejestrację, logowanie oraz obliczanie BMR (Basal Metabolic Rate) na podstawie danych takich jak waga, wzrost, wiek i płeć. Dodatkowo użytkownicy mogą dodawać własne przepisy kulinarne, zarządzać nimi i przeglądać dodane przepisy. 

## Funkcje:
- Rejestracja i logowanie użytkowników.
- Obliczanie BMR na podstawie wprowadzonych danych (waga, wzrost, wiek, płeć).
- Dodawanie przepisów kulinarnych.
- Przeglądanie przepisów.

## Testy Manualne


| ID     | Tytuł                                | Warunki początkowe        | Kroki testowe                                                                 | Oczekiwany rezultat                                 |
|--------|--------------------------------------|---------------------------|-------------------------------------------------------------------------------|----------------------------------------------------|
| TC001  | Rejestracja z poprawnymi danymi      | Strona rejestracji otwarta | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Register”. | Użytkownik zostaje zarejestrowany.                |
| TC002  | Rejestracja z brakującymi danymi     | Strona rejestracji otwarta | 1. Zostaw pole „email” puste. <br> 2. Kliknij „Register”.                    | Komunikat: „Enter email”.                         |
| TC003  | Logowanie z poprawnymi danymi       | Strona logowania otwarta  | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Login”.  | Użytkownik zostaje zalogowany.                    |
| TC004  | Logowanie z błędnymi danymi         | Strona logowania otwarta  | 1. Wprowadź nieprawidłowe hasło. <br> 2. Kliknij „Login”.                   | Komunikat: „Authentication failed”.                |
| TC005  | Rejestracja z brakującymi danymi     | Strona rejestracji otwarta | 1. Zostaw pole „email” puste. <br> 2. Kliknij „Register”.                    | Komunikat: „Enter email”.                         |
| TC006  | Logowanie z poprawnymi danymi       | Strona logowania otwarta  | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Login”.  | Użytkownik zostaje zalogowany.                    |
| TC007  | Rejestracja z poprawnymi danymi      | Strona rejestracji otwarta | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Register”. | Użytkownik zostaje zarejestrowany.                |
| TC008  | Rejestracja z brakującymi danymi     | Strona rejestracji otwarta | 1. Zostaw pole „email” puste. <br> 2. Kliknij „Register”.                    | Komunikat: „Enter email”.                         |
| TC009  | Logowanie z poprawnymi danymi       | Strona logowania otwarta  | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Login”.  | Użytkownik zostaje zalogowany.                    |
| TC010  | Rejestracja z poprawnymi danymi      | Strona rejestracji otwarta | 1. Wprowadź poprawny email. <br> 2. Wprowadź hasło. <br> 3. Kliknij „Register”. | Użytkownik zostaje zarejestrowany.                
